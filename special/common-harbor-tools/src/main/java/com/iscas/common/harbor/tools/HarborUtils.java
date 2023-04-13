package com.iscas.common.harbor.tools;

import com.iscas.common.harbor.tools.client.OkHttpCustomClient;
import com.iscas.common.harbor.tools.client.OkHttpProps;
import com.iscas.common.harbor.tools.exception.CallHarborException;
import com.iscas.common.harbor.tools.model.ModuleHealth;
import com.iscas.common.harbor.tools.model.Project;
import com.iscas.common.harbor.tools.model.Repository;
import com.iscas.common.harbor.tools.model.Tag;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.common.web.tools.json.JsonUtils;
import okhttp3.Credentials;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.function.BiFunction;

/**
 * <>访问Harbor的工具类</>
 * <>可以从Harbor网址中进入swagger查看restful接口</>
 * <>也可以通过https://editor.swagger.io/打开swagger.yaml查看</>
 * <>可以通过以上两种方式丰富工具</>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/8 18:39
 * @since jdk1.8
 */
public class HarborUtils {
    private static String username = "admin";
    private static String pwd = "Harbor12345";
    private static String url = "http://172.16.10.160:88/api";
    private static OkHttpCustomClient httpClient = new OkHttpCustomClient(new OkHttpProps());
    private static String dateTimePattern = "yyyy-MM-dd'T'HH:mm:ss";

    public static void setProp(String username, String password, String url) {
        HarborUtils.username = username;
        HarborUtils.pwd = password;
        HarborUtils.url = url;
    }


    private HarborUtils() {}

    /**
     * 查看Harbor各个组件模块健康状况
     * */
    public static List<ModuleHealth> health() throws IOException, CallHarborException {
        List<ModuleHealth> moduleHealths = new ArrayList<>();
        String visitUrl = url + "/health";
        String result = httpClient.doGet(visitUrl);
        throwHtmlResultError(result);
        Map map = JsonUtils.fromJson(result, Map.class);
        if (MapUtils.isNotEmpty(map) && map.containsKey("components")) {
            List list = (List) map.get("components");
            if (CollectionUtils.isNotEmpty(list)) {
                for (Object o : list) {
                    Map healthMap = (Map) o;
                    ModuleHealth moduleHealth = new ModuleHealth();
                    moduleHealth.setName((String) healthMap.get("name"));
                    moduleHealth.setStatus((String) healthMap.get("status"));
                    moduleHealths.add(moduleHealth);
                }
            }
        }
        return moduleHealths;
    }

    /**
     * 查看Harbor的project,模糊查询
     * */
    public static List<Project> searchProject(String projectName) throws IOException, CallHarborException {
        List<Project> projects = new ArrayList<>();
        String visitUrl = url + "/search" ;
        if (StringUtils.isNotEmpty(projectName)) {
            visitUrl += "?q=" + projectName;
        }
        String result = httpClient.doGet(visitUrl);
        throwHtmlResultError(result);
        analyzeResultErrorCode(result, "500", "查询工程出现未知错误");
        Map map = JsonUtils.fromJson(result, Map.class);
        if (MapUtils.isNotEmpty(map)) {
            List<Map> projectMaps = (List<Map>) map.get("project");
            if (CollectionUtils.isNotEmpty(projectMaps)) {
                projects = Json2ObjUtils.json2List(Project.class, projectMaps, createProjectBiFunction(),
                        "project_id", "projectId", "owner_id", "ownerId", "name", "name",
                        "creation_time", "createTime", "update_time", "updateTime", "deleted", "deleted",
                        "repo_count", "repoCount", "metadata", "projectPublic");
            }
        }
        return projects;
    }

    /**
     * 查看Harbor的repository(镜像),模糊查询
     * */
    public static List<Repository> searchRepo(String repoName) throws IOException, CallHarborException {
        List<Repository> repos = new ArrayList<>();
        String visitUrl = url + "/search" ;
        if (StringUtils.isNotEmpty(repoName)) {
            visitUrl += "?q=" + repoName;
        }
        String result = httpClient.doGet(visitUrl);
        throwHtmlResultError(result);
        analyzeResultErrorCode(result, "500", "查询仓库出现未知错误");
        Map map = JsonUtils.fromJson(result, Map.class);
        if (MapUtils.isNotEmpty(map)) {
            List<Map> repoMaps = (List<Map>) map.get("repository");
            if (CollectionUtils.isNotEmpty(repoMaps)) {
                repos = Json2ObjUtils.json2List(Repository.class, repoMaps, null,
                        "project_id", "projectId", "project_name", "projectName", "project_public", "projectPublic",
                        "pull_count", "pullCount", "repository_name", "name", "tags_count", "tagsCount");
            }
        }
        return repos;
    }

    /** 创建一个project*/
    public static void createProject(Project project) throws IOException, CallHarborException {
        String visitUrl = url + "/projects";
        JSONObject request = new JSONObject();
        request.put("project_name", project.getName());
        request.put("count_limit", project.getRepoCount());
        JSONObject metadata = new JSONObject();
        metadata.put("public", project.getProjectPublic() ? "1" : "0");
        request.put("metadata", metadata);
        String requestyBody = request.toString();
        String s = httpClient.doPost(visitUrl, getCredentialHeader(), requestyBody);
        throwHtmlResultError(s);
        analyzeResultErrorCode(s, "400", "请求不符合格式要求", "401", "未登录", "409", "工程名称已存在",
                "415", "content-type必须是application/json", "500", "创建工程出现未知错误");
    }

    /** 按照project的ID获取project的信息 */
    public static Project getProjectById(int projectId) throws IOException, CallHarborException {
        String visitUrl = url + "/projects/" + projectId;
        String result = httpClient.doGet(visitUrl, getCredentialHeader());
        throwHtmlResultError(result);
        analyzeResultErrorCode(result, "500", "查询工程出现未知错误", "401", "未登录");
        Project project = Json2ObjUtils.json2Obj(Project.class, JsonUtils.fromJson(result, Map.class), createProjectBiFunction(),
                "project_id", "projectId", "owner_id", "ownerId", "name", "name",
                "creation_time", "createTime", "update_time", "updateTime", "deleted", "deleted",
                "repo_count", "repoCount", "metadata", "projectPublic");
        return project;
    }

    /**
     * 获取一个镜像下的tag
     * */
    public static List<Tag> getTags(String repoName) throws IOException, CallHarborException {
        List<Tag> tags = new ArrayList<>();
        String visitUrl = url + "/repositories/" + repoName + "/tags";
        String result = httpClient.doGet(visitUrl, getCredentialHeader());
        throwHtmlResultError(result);
        analyzeResultErrorCode(result, "500", "获取tag出现未知错误", "401", "未登录", "404", String.format("镜像:[%s]对应的工程不存在", repoName));

        List<Map> list = JsonUtils.fromJson(result, List.class);
        if (CollectionUtils.isNotEmpty(list)) {
            tags = Json2ObjUtils.json2List(Tag.class, list, createTagBiFunction(),
                    "digest", "digest", "name", "name", "size", "size",
                    "architecture", "architecture", "os", "os", "os.version", "osVersion",
                    "docker_version", "dockerVersion", "created", "createTime",
                    "push_time", "pushTime", "pull_time", "pullTime");
        }
        return tags;
    }


    /**
     * 删除一个镜像，将会把所有标签都删除
     * */
    public static boolean deleteRepo(String repoName) throws IOException, CallHarborException {
        String visitUrl = url + "/repositories/" + repoName;
        String result = httpClient.doDelete(visitUrl, getCredentialHeader());
        throwHtmlResultError(result);
        analyzeResultErrorCode(result, "500", "删除镜像出现未知错误", "401", "未登录", "404", String.format("镜像:[%s]或对应的工程不存在", repoName));
        return true;
    }

    /**
     * 删除一个镜像的标签
     * */
    public static boolean deleteRepoTag(String repoName, String tag) throws IOException, CallHarborException {
        String visitUrl = url + "/repositories/" + repoName + "/tags/" + tag;
        String result = httpClient.doDelete(visitUrl, getCredentialHeader());
        throwHtmlResultError(result);
        analyzeResultErrorCode(result, "500", "删除镜像的标签出现未知错误", "401", "未登录", "404", String.format("镜像:[%s]或对应的工程不存在", repoName));
        return true;
    }

    private static void throwHtmlResultError(String s) throws CallHarborException {
        if (s != null && s.startsWith("<!DOCTYPE HTML>")) {
            throw new CallHarborException("调用Harbor出错");
        }
    }

    private static Map<String, String> getCredentialHeader() {
        Map<String, String> map = new HashMap<>();
        String credential = Credentials.basic(username, pwd);
        map.put("Authorization", credential);
        return map;
    }

    private static void analyzeResultErrorCode(String s, String ... codeMsg) throws CallHarborException {
        if (Objects.equals("", s)) {
            return;
        }
        Map resultMap = null;
        try {
            if (s.startsWith("[")) return;
            resultMap = JsonUtils.fromJson(s, Map.class);
        } catch (Throwable e) {
            return;
        }
        Integer code = (Integer) resultMap.get("code");
        for (int i = 0; i < codeMsg.length; i = i + 2) {
            if (Objects.equals(code + "", codeMsg[i])) {
                throw new CallHarborException(codeMsg[i + 1]);
            }
        }

    }

    private static BiFunction<String, Object, Object> createProjectBiFunction() {
        BiFunction<String, Object, Object> biFunction = (s, s2) -> {
            if (Objects.equals("createTime", s) || Objects.equals("updateTime", s)) {
                if (s2 != null) {
                    Date date = null;
                    try {
                        date = DateSafeUtils.parse(s2.toString().substring(0, 18), dateTimePattern);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    return date;
                }
            } else if (Objects.equals("projectPublic", s)) {
                Map meta = (Map) s2;
                return Boolean.valueOf((String) meta.get("public"));
            }
            return s2;
        };
        return biFunction;
    }

    private static BiFunction<String, Object, Object> createTagBiFunction() {
        BiFunction<String, Object, Object> biFunction = (s, s2) -> {
            if (Objects.equals("createTime", s) || Objects.equals("pushTime", s) || Objects.equals("pullTime", s)) {
                if (s2 != null) {
                    Date date = null;
                    try {
                        date = DateSafeUtils.parse(s2.toString().substring(0, 18), dateTimePattern);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    return date;
                }
            }
            return s2;
        };
        return biFunction;
    }

}
