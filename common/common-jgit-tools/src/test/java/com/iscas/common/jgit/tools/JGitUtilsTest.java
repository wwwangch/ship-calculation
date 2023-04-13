package com.iscas.common.jgit.tools;

import com.iscas.common.jgit.tools.exception.JGitException;
import com.iscas.common.jgit.tools.model.CompareResult;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/24 10:31
 * @since jdk1.8
 */
public class JGitUtilsTest {

    @Test
    public void initTest() throws JGitException {
        JGitUtils.gitInit("d:/tmp/jgit");
    }

    @Test
    public void cloneTest() throws JGitException {
        JGitUtils.gitClone("http://172.16.10.190:8090/zhuquanwen/data-collect.git",
                "d:/tmp/jgit2", "master", "zhuquanwen", "zhuquanwen");
    }

    @Test
    public void addTest() throws JGitException {
        JGitUtils.gitAdd("aaa.txt", "D:/tmp/jgit");
    }

    @Test
    public void commitTest() throws JGitException {
        JGitUtils.gitCommit("commit3!!", "D:/tmp/jgit");
    }

    @Test
    public void pullTest() throws JGitException {
        JGitUtils.gitPull("master", "D:/tmp/jgit");
    }

    @Test
    public void pushTest() throws JGitException {
        JGitUtils.gitPush("http://172.16.10.190:8090/zhuquanwen/data-collect.git", "D:/tmp/jgit",
                "master", "zhuquanwen", "zhuquanwen");
    }

    @Test
    public void branchATest() throws JGitException {
        List<Ref> refs = JGitUtils.gitBranchA("d:/tmp/jgit");
        for (Ref ref : refs) {
            System.out.println("ref:" + ref);
            System.out.println("name:" + ref.getName());
            System.out.println("leafName:" + ref.getLeaf().getName());
            System.out.println("targetName:" + ref.getTarget().getName());
            System.out.println("objectId:" + ref.getObjectId());
        }
    }

    @Test
    public void branchRTest() throws JGitException {
        List<Ref> refs = JGitUtils.gitBranchR("d:/tmp/jgit");
        for (Ref ref : refs) {
            System.out.println("ref:" + ref);
            System.out.println("name:" + ref.getName());
            System.out.println("leafName:" + ref.getLeaf().getName());
            System.out.println("targetName:" + ref.getTarget().getName());
            System.out.println("objectId:" + ref.getObjectId());
        }
    }

    @Test
    public void branchTest() throws JGitException {
        List<Ref> refs = JGitUtils.gitBranch("d:/tmp/jgit");
        for (Ref ref : refs) {
            System.out.println("ref:" + ref);
            System.out.println("name:" + ref.getName());
            System.out.println("leafName:" + ref.getLeaf().getName());
            System.out.println("targetName:" + ref.getTarget().getName());
            System.out.println("objectId:" + ref.getObjectId());
        }
    }

    @Test
    public void checkoutTest() throws JGitException {
        Ref ref = JGitUtils.gitCheckout("d:/tmp/jgit", "test");
        System.out.println("ref:" + ref);
        System.out.println("name:" + ref.getName());
        System.out.println("leafName:" + ref.getLeaf().getName());
        System.out.println("targetName:" + ref.getTarget().getName());
        System.out.println("objectId:" + ref.getObjectId());
    }

    @Test
    public void checkoutBTest() throws JGitException {
        Ref ref = JGitUtils.gitCheckoutB("d:/tmp/jgit", "test");
        System.out.println("ref:" + ref);
        System.out.println("name:" + ref.getName());
        System.out.println("leafName:" + ref.getLeaf().getName());
        System.out.println("targetName:" + ref.getTarget().getName());
        System.out.println("objectId:" + ref.getObjectId());
    }

    @Test
    public void statusTest() throws JGitException {
        Status status = JGitUtils.gitStatus("d:/tmp/jgit");
        System.out.println(status);
    }

    @Test
    public void logTest() throws JGitException {
        Iterable<RevCommit> revCommits = JGitUtils.gitLog("D:/tmp/jgit");
        Iterator<RevCommit> iterator = revCommits.iterator();
        while (iterator.hasNext()) {
            RevCommit next = iterator.next();
            System.out.println("commitinfo:" + next);
            System.out.println("commit id:" + next.getId());
            System.out.println("commit name:" + next.getName());
            System.out.println("commit time:" + next.getCommitTime());
            System.out.println("commit fullMessage:" + next.getFullMessage());
            System.out.println("commit shortMessage:" + next.getShortMessage());

        }
    }

    @Test
    public void diff() throws JGitException {
        List<String> diffs = JGitUtils.diff("eb53e57f8fdab0fc9ee3115b604f702e6a3f0bf0", "50fcc23cbb8b6234c74cac836022c0e20895a6eb",
                "C:/ideaProjects/newframe/common/common-jgit-tools/tmp/111");
        System.out.println(diffs);
    }

    @Test
    public void compare() throws JGitException {
        List<CompareResult> compare = JGitUtils.compare("1.翁违规违规1111\n" +
                        "2.违规为各位\n" +
                        "\n" +
                        "3.违wgwe位\n" +
                        "\n" +
                        "4.违规为各位\n" +
                        "5.wegweg\n" +
                        "6.wehwweh",
                "1.翁违规违规\n" +
                        "2.违规为各位\n" +
                        "3.违规为各位\n" +
                        "4.违规为各位");
        System.out.println(compare);
    }

}