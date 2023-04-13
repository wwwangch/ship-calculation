package com.iscas.common.tools;

import com.iscas.common.tools.arithmetic.FloatExactArithUtilsTests;
import com.iscas.common.tools.assertion.AssertArrayUtilsTests;
import com.iscas.common.tools.assertion.AssertObjUtilsTests;
import com.iscas.common.tools.captcha.CaptchaTests;
import com.iscas.common.tools.core.collection.CollectionRaiseUtilsTests;
import com.iscas.common.tools.core.collection.MapRaiseUtilsTests;
import com.iscas.common.tools.core.date.DateRaiseUtilsTests;
import com.iscas.common.tools.core.date.DateSafeUtilsTests;
import com.iscas.common.tools.core.io.file.FileTypeUtilsTests;
import com.iscas.common.tools.core.io.file.FileUtilsTests;
import com.iscas.common.tools.core.io.file.JarPathUtilsTests;
import com.iscas.common.tools.core.io.file.ThumbnailPicUtilsTests;
import com.iscas.common.tools.core.io.serial.JdkSerializableUtilsTests;
import com.iscas.common.tools.core.io.serial.KryoSerializerTests;
import com.iscas.common.tools.core.io.zip.GzipUtilsTests;
import com.iscas.common.tools.core.object.ObjectsUtilsTests;
import com.iscas.common.tools.core.random.RandomBaseInfoUtilsTests;
import com.iscas.common.tools.core.random.RandomStringUtilsTests;
import com.iscas.common.tools.core.reflect.ReflectUtilsTests;
import com.iscas.common.tools.core.security.*;
import com.iscas.common.tools.core.string.StringRaiseUtilsTests;
import com.iscas.common.tools.core.valid.IpValidUtilsTests;
import com.iscas.common.tools.csv.CsvUtilsTests;
import com.iscas.common.tools.emoji.EmojiTests;
import com.iscas.common.tools.exception.LambdaExceptionUtilsTests;
import com.iscas.common.tools.gis.LatLonUtilsTests;
import com.iscas.common.tools.guava.BloomFilterTest;
import com.iscas.common.tools.hutool.aop.AopTest;
import com.iscas.common.tools.hutool.bloomfilter.BitMapBloomFilterTest;
import com.iscas.common.tools.hutool.cache.*;
import com.iscas.common.tools.hutool.core.ArrayUtilTests;
import com.iscas.common.tools.hutool.core.CloneTest;
import com.iscas.common.tools.hutool.core.ConvertTest;
import com.iscas.common.tools.hutool.core.DateTest;
import com.iscas.common.tools.hutool.excel.ExcelExportTests;
import com.iscas.common.tools.jdk.IntegerTests;
import com.iscas.common.tools.office.excel.ExcelUtilsTests;
import com.iscas.common.tools.office.excel.easyexcel.read.EasyExcelReadTests;
import com.iscas.common.tools.office.excel.easyexcel.write.EasyExcelWriteTests;
import com.iscas.common.tools.pagination.MemoryPageUtilsTests;
import com.iscas.common.tools.pinyin.PinyinUtilsTests;
import com.iscas.common.tools.tuples.JavaTuplesTests;
import com.iscas.common.tools.url.UrlMatcherTests;
import com.iscas.common.tools.xml.Dom4jUtilsTests;
import com.iscas.common.tools.yaml.YamlUtilsTests;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;


/**
 *  Junit5所有的单元测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/28 21:21
 * @since jdk1.8
 */


@RunWith(JUnitPlatform.class)
@SelectClasses({
        FileUtilsTests.class, FloatExactArithUtilsTests.class,
        AssertArrayUtilsTests.class, AssertObjUtilsTests.class,
        CaptchaTests.class, /*JarClassloaderTests.class,*/
        CollectionRaiseUtilsTests.class, MapRaiseUtilsTests.class,
        DateRaiseUtilsTests.class, DateSafeUtilsTests.class,
        FileTypeUtilsTests.class, FileUtilsTests.class,
        JarPathUtilsTests.class, /*LargeFileTests.class,*/
        ThumbnailPicUtilsTests.class, /*RarUtilsTests.class,*/
        JdkSerializableUtilsTests.class, KryoSerializerTests.class,
        GzipUtilsTests.class, /*ZipUtilsTests.class,*/
        ObjectsUtilsTests.class, RandomBaseInfoUtilsTests.class,
        RandomStringUtilsTests.class, ReflectUtilsTests.class,
        AesUtilsTests.class, Base64JavaUtilsTests.class,
        BCryptUtilsTests.class, EscapeUtilsTests.class,
        MD5UtilsTests.class, Pbkdf2Sha256UtilsTests.class,
        RsaUtilsTests.class, Sha256UtilsTests.class,
        URLCoderUtilsTests.class, StringRaiseUtilsTests.class,
        IpValidUtilsTests.class, CsvUtilsTests.class,
        EmojiTests.class, LambdaExceptionUtilsTests.class,
        LatLonUtilsTests.class, /*GradleRepo2MavenRepoUtilsTests.class,*/
        BloomFilterTest.class, /*HtmlSuckerTests.class,*/
        AopTest.class, BitMapBloomFilterTest.class,
        FIFOCacheTest.class, FileCacheTest.class,
        LFUCacheTest.class, LRUCacheTest.class,
        TimeCacheTest.class, ArrayUtilTests.class,
        CloneTest.class, ConvertTest.class,
        DateTest.class, ExcelExportTests.class,
        /*Ip2RegionUtilsTests.class,*/ IntegerTests.class,
        EasyExcelReadTests.class, EasyExcelWriteTests.class,
        ExcelUtilsTests.class, /*Template2DocUtilsTests.class,*/
        /*Template2DocxUtilsTests.class,*/ MemoryPageUtilsTests.class,
        /*LinuxWord2PDFTests.class,*/ PinyinUtilsTests.class,
        /*CompletableFutrueTests.class,*/ JavaTuplesTests.class,
        UrlMatcherTests.class, /*URLTests.class,*/
        Dom4jUtilsTests.class, YamlUtilsTests.class

})
public class AllTests5 {
}
