package com.iscas.common.jgit.tools;

import com.iscas.common.jgit.tools.exception.JGitException;
import com.iscas.common.jgit.tools.model.CompareResult;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/24 10:27
 * @since jdk1.8
 */
public class JGitUtils {

    /**
     * git init
     */
    public static void gitInit(String localRepoPath) throws JGitException {
        Repository newlyCreatedRepo = null;
        try {
            newlyCreatedRepo = FileRepositoryBuilder.create(new File(localRepoPath + "/.git"));
            newlyCreatedRepo.create();
        } catch (IOException e) {
            throw new JGitException(e);
        } finally {
            if (newlyCreatedRepo != null) {
                newlyCreatedRepo.close();
            }
        }
    }


    /**
     * git clone 克隆远程仓库
     *
     * @param remoteRepoPath:远端仓库url
     * @param branch：分支
     * @param userName：用户名
     * @param passWord：密码
     */
    public static void gitClone(String remoteRepoPath, String localRepoPath, String branch, String userName,
                                String passWord) throws JGitException {
        //设置远程服务器上的用户名和密码
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider
                = new UsernamePasswordCredentialsProvider(userName, passWord);
        //克隆代码库命令
        CloneCommand cloneCommand = Git.cloneRepository();
        Git git = null;

        try {
            git = cloneCommand.setURI(remoteRepoPath) //设置远程URI
                    .setBranch(branch) //设置clone下来的分支
                    .setDirectory(new File(localRepoPath)) //设置下载存放路径
                    .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
                    .call();
        } catch (GitAPIException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }


    /**
     * git add 添加文件
     *
     * @param addFilePath:添加文件路径
     * @param localRepoPath：分支
     * @return boolean:结果
     */
    public static boolean gitAdd(String addFilePath, String localRepoPath) throws JGitException {
        boolean addFileFlag = true;
        Git git = null;

        try {
            git = Git.open(new File(localRepoPath + "/.git"));
            //add file to git
            git.add().addFilepattern(addFilePath).call();
        } catch (IOException | GitAPIException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
        return addFileFlag;
    }

    /**
     * git commit 本地代码提交
     *
     * @param msg:提交信息
     * @param localRepoPath:本地代码仓位置
     */
    public static void gitCommit(String msg, String localRepoPath) throws JGitException {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            //全部提交
            git.commit().setAll(true).setMessage(msg).call();
        } catch (IOException | GitAPIException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }

    /**
     * git pull 更新代码
     *
     * @param branch:提交信息
     * @param localRepoPath:提交信息
     * @return boolean:提交结果
     */
    public static void gitPull(String branch, String localRepoPath) throws JGitException {
        try (Git git = Git.open(new File(localRepoPath + "/.git"))) {
            git.pull().setRemoteBranchName(branch).call();
        } catch (GitAPIException | IOException e) {
            throw new JGitException(e);
        }
    }


    /**
     * git push 推送本地代码到远程仓库
     *
     * @param remoteRepoPath：远程仓库地址
     * @param localRepoPath：本地仓库地址
     * @param branch：分支
     * @param userName：用户名
     * @param passWord：密码
     */
    public static void gitPush(String remoteRepoPath, String localRepoPath, String branch,
                               String userName, String passWord) throws JGitException {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider
                    = new UsernamePasswordCredentialsProvider(userName, passWord);
            git.push()
                    .setRemote(remoteRepoPath)
                    .setRefSpecs(new RefSpec(branch))
                    .setCredentialsProvider(usernamePasswordCredentialsProvider)
                    .call();
        } catch (GitAPIException | IOException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }

    /**
     * git log 获取git提交历史
     *
     * @param localRepoPath：本地仓库地址
     */
    public static Iterable<RevCommit> gitLog(String localRepoPath) throws JGitException {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            return git.log().call();
        } catch (GitAPIException | IOException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }

    /**
     * git branch -a 获取所有git的分支
     *
     * @param localRepoPath：本地仓库地址
     */
    public static List<Ref> gitBranchA(String localRepoPath) throws JGitException {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            return git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
        } catch (GitAPIException | IOException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }

    /**
     * git branch 获取本地git的分支
     *
     * @param localRepoPath：本地仓库地址
     */
    public static List<Ref> gitBranch(String localRepoPath) throws JGitException {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            return git.branchList().call();
        } catch (GitAPIException | IOException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }

    /**
     * git branch -r 获取远程git的分支
     *
     * @param localRepoPath：本地仓库地址
     */
    public static List<Ref> gitBranchR(String localRepoPath) throws JGitException {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            return git.branchList().setListMode(ListBranchCommand.ListMode.REMOTE).call();
        } catch (GitAPIException | IOException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }

    /**
     * git checkout  切换到已有的本地分支
     *
     * @param localRepoPath：本地仓库地址
     */
    public static Ref gitCheckout(String localRepoPath, String branchName) throws JGitException {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            return git.checkout().setName(branchName).call();
        } catch (GitAPIException | IOException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }

    /**
     * git checkout -b 创建并切换分支
     *
     * @param localRepoPath：本地仓库地址
     */
    public static Ref gitCheckoutB(String localRepoPath, String branchName) throws JGitException {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            return git.checkout().setCreateBranch(true).setName(branchName).call();
        } catch (GitAPIException | IOException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }

    /**
     * git status
     *
     * @param localRepoPath：本地仓库地址
     */
    public static Status gitStatus(String localRepoPath) throws JGitException {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            return git.status().call();
        } catch (GitAPIException | IOException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }

    /**
     * 一个基本的 Git Diff 格式如下：
     * diff --git a/test.txt b/test.txt
     * index 0eb7e4c..d855047 100644
     * --- a/test.txt
     * +++ b/test.txt
     *
     * @param childId：子版本号
     * @param parentId：主版本号
     * @param localRepoPath：本地路径
     * @@ -1,3 +1,6 @@
     * -123
     * +12
     * 456
     * -789
     * \ No newline at end of file
     * +7891
     * +2222
     * +
     * +wgweg
     * \ No newline at end of file
     * <p>
     * 第一行是 Git Diff 的 header，进行比较的是 a 版本的 test.txt（变动前）和 b 版本的 test.txt（变动后）。
     * <p>
     * 第二行是两个版本的 hash 值以及文件模式（100644 表示是文本文件）。
     * <p>
     * 第三、四行表示进行比较的两个文件，--- 表示变动前的版本，+++ 表示变动后的版本。
     * <p>
     * 第五行是一个 thunk header（可能会有多个），提供变动的”上下文“（context），-1,7表示接下来展示变动前文件第一至第七行，+1,7 表示接下来展示变动后文件第一至第七行。
     * <p>
     * 接下来的几行就是具体的变动内容。它将两个文件的上下文合并显示在一起，每一行前面是一个标志位，''（空）表示无变化（是一个上下文行）、- 表示变动前文件删除的行、+ 表示变动后文件新增的行。
     */
    public static List<String> diff(String childId, String parentId, String localRepoPath) throws JGitException {
        Git git = null;
        try {
            git = Git.open(new File(localRepoPath + "/.git"));
            Repository repository = git.getRepository();
            ObjectReader reader = repository.newObjectReader();
            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();

            ObjectId old = repository.resolve(childId + "^{tree}");
            ObjectId head = repository.resolve(parentId + "^{tree}");
            oldTreeIter.reset(reader, old);
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            newTreeIter.reset(reader, head);
            List<DiffEntry> diffs = git.diff()
                    .setNewTree(newTreeIter)
                    .setOldTree(oldTreeIter)
                    .call();
            List<String> diffStrs = new ArrayList<>();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try (DiffFormatter df = new DiffFormatter(out)) {
                df.setRepository(git.getRepository());
                for (DiffEntry diffEntry : diffs) {
                    df.format(diffEntry);
                    String diffText = out.toString(StandardCharsets.UTF_8);
                    diffStrs.add(diffText);
                    out.reset();
                }
            }
            return diffStrs;
        } catch (IOException | GitAPIException e) {
            throw new JGitException(e);
        } finally {
            close(git);
        }
    }

    /**
     * git branch 获取本地git的分支
     *
     * @param newStr：新文本
     * @param oldStr：旧文本
     */
    public static List<CompareResult> compare(String newStr, String oldStr) throws JGitException {
        File tmpFile = new File("tmp", UUID.randomUUID().toString());
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        List<CompareResult> compareResults = new ArrayList<>();
        try {
            String localRepPath = tmpFile.getAbsolutePath();
            //初始化git
            gitInit(localRepPath);
            File file = new File(tmpFile, "compare.txt");
            String filePath = file.getAbsolutePath();

            writeStr(file, oldStr);
            //提交旧版本
            gitAdd("compare.txt", localRepPath);
            gitCommit("older commit", localRepPath);

//            gitAdd("compare.txt", localRepPath);
            writeStr(file, newStr);
            //提交新版本
            gitCommit("newer commit", localRepPath);

            //获取提交日志
            Iterable<RevCommit> revCommits = gitLog(localRepPath);
            //获取最近的两次提交
            Iterator<RevCommit> iterator = revCommits.iterator();
            RevCommit revCommit1 = iterator.next();
            RevCommit revCommit2 = iterator.next();
            String newId = revCommit1.getName();
            String oldId = revCommit2.getName();
            List<String> diff = diff(oldId, newId, localRepPath);
            String regex = "^@@.*@@$";
            Pattern pattern = Pattern.compile(regex);
            if (diff != null) {
                String diffStr = diff.get(0);
                String[] diffStrArray = diffStr.split("\n");
                int olderIndex = 0;
                int newerIndex = 0;
                CompareResult compareResult = new CompareResult();
                boolean beginAnalyze = false;
                for (String s : diffStrArray) {
                    if (pattern.matcher(s).matches()) {
                        beginAnalyze = true;
                        continue;
                    }
                    if (s.contains("No newline at end of file")) {
                        continue;
                    }
                    if (beginAnalyze) {
                        //开始统计异同
                        if (s.startsWith(" ") && !(s.startsWith("-") || s.startsWith("+"))) {
                            if (addCompareResult(compareResults, compareResult, olderIndex, newerIndex)) {
                                compareResult = new CompareResult();
                            }
                            olderIndex++;
                            newerIndex++;
                        } else {
                            if (s.startsWith("-")) {
                                olderIndex++;
                                if (compareResult.getCFrom() == null) {
                                    compareResult.setCFrom(olderIndex);
                                }
                            } else if (s.startsWith("+")) {
                                newerIndex++;
                                if (compareResult.getPFrom() == null) {
                                    compareResult.setPFrom(newerIndex);
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                addCompareResult(compareResults, compareResult, olderIndex, newerIndex);
            }
            return compareResults;
        } finally {
            deleteDir(tmpFile);
        }
    }

    private static boolean addCompareResult(List<CompareResult> compareResults, CompareResult compareResult,
                                            int olderIndex, int newerIndex) {
        if ((compareResult.getCFrom() != null || compareResult.getPFrom() != null) &&
                (olderIndex != 0 || newerIndex != 0)) {
            compareResult.setCTo(olderIndex);
            compareResult.setPTo(newerIndex);
            if (compareResult.getPFrom() == null && compareResult.getPTo() != null) {
                compareResult.setPFrom(compareResult.getPTo());
            }
            if (compareResult.getCFrom() == null && compareResult.getCTo() != null) {
                compareResult.setCFrom(compareResult.getCTo());
            }
            compareResults.add(compareResult);
            return true;
        }
        return false;
    }

    private static void writeStr(File file, String str) throws JGitException {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(str);
        } catch (IOException e) {
            throw new JGitException(e);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    private static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            if (children != null) {
                Arrays.stream(children).forEach(subDir -> deleteDir(new File(dir, subDir)));
            }
        }
        // 目录此时为空，可以删除
        dir.delete();
    }

    private static void close(Git git) {
        if (git != null) {
            git.close();
        }
    }
}
