package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.Section;
import com.iscas.biz.calculation.service.SectionService;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.TableSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:28
 */
@RestController
@RequestMapping("/section")
@Slf4j
@Tag(name = "剖面控制器")
public class SectionController {
    private final static String TABLE_IDENTITY = "section";

    private final SectionService sectionService;

    private final TableDefinitionService tableDefinitionService;

    public SectionController(SectionService sectionService, TableDefinitionService tableDefinitionService) {
        this.sectionService = sectionService;
        this.tableDefinitionService = tableDefinitionService;
    }

    @Operation(summary = "获取表头", description = "不带数据，带下拉列表")
    @GetMapping(value = "/getHeader", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTableHeaderWithOption() throws BaseException {
        return tableDefinitionService.getHeaderWithOption(TABLE_IDENTITY);
    }

    @Operation(summary = "查询表格数据", description = "不带表头")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "查询条件",
            content = @Content(schema = @Schema(implementation = TableSearchRequest.class)))
    @PostMapping(value = "/getData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getData(@RequestBody TableSearchRequest request)
            throws ValidDataException {
        return tableDefinitionService.getData(TABLE_IDENTITY, request, null);
    }

    @Operation(summary = "删除参数数据", description = "根据主键删除数据")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "id的集合",
            content = @Content(examples = @ExampleObject(value = "[123, 124]")))
    @PostMapping("/del")
    public Boolean deleteData(@RequestBody List<Integer> ids) {
        return sectionService.deleteByIds(ids);
    }

    @Operation(summary = "新增", description = "插入")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
    @PostMapping(value = "/data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Integer saveData(@RequestParam(value = "project_id") Integer projectId,
                            @RequestParam(value = "x_coordinate") Double xCoordinate,
                            @RequestParam(value = "section_file") MultipartFile sectionFile,
                            @RequestParam(value = "component_span") Double componentSpan) throws IOException {
        Section section = new Section();
        section.setProjectId(projectId);
        section.setXCoordinate(xCoordinate);
        section.setSectionFile(sectionFile);
        section.setComponentSpan(componentSpan);
        return sectionService.save(section);
    }

    @Operation(summary = "修改数据", description = "更新")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "修改的数据(未变动的数据也传)",
            content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
    @PutMapping(value = "/data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Integer editData(@RequestParam(value = "section_id") Integer sectionId,
                            @RequestParam(value = "project_id") Integer projectId,
                            @RequestParam(value = "x_coordinate") Double xCoordinate,
                            @RequestParam(value = "section_file", required = false) MultipartFile sectionFile,
                            @RequestParam(value = "section_file_name") String sectionFileName,
                            @RequestParam(value = "section_file_path") String sectionFilePath,
                            @RequestParam(value = "component_span") Double componentSpan) throws IOException {
        Section section = new Section();
        section.setSectionId(sectionId);
        section.setProjectId(projectId);
        section.setXCoordinate(xCoordinate);
        section.setSectionFile(sectionFile);
        section.setComponentSpan(componentSpan);
        section.setSectionFileName(sectionFileName);
        section.setSectionFilePath(sectionFilePath);
        return sectionService.update(section);
    }

}
