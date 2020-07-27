package com.ssafy.devlog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devlog.dto.PortfolioProject;
import com.ssafy.devlog.dto.Project;
import com.ssafy.devlog.service.PortfolioProjectService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/portfoliopjt")
public class PortfolioProjectController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostCommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PortfolioProjectService portfolioProjectService;
	
	@ApiOperation(value = "포트폴리오의 모든 프로젝들을 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post_portfolio}")
	public ResponseEntity<List<Project>> selectAllPortfolioProject(@PathVariable int seq_post_portfolio) throws Exception {
		logger.debug("selectAllPortfolioProject - 호출");
		return new ResponseEntity<List<Project>>(portfolioProjectService.selectAllPortfolioProject(seq_post_portfolio), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포트폴리오에 프로젝트를 추가한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertPortfolioProject(@RequestBody PortfolioProject portfolioProject) throws Exception {
		logger.debug("insertPortfolioProject - 호출");
		if(portfolioProjectService.insertPortfolioProject(portfolioProject)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "포트폴리오의 프로젝트를 삭제한다.", response = String.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deletePortfolioProject(@PathVariable int seq) throws Exception {
		logger.debug("deletePortfolioProject - 호출");
		if(portfolioProjectService.deletePortfolioProject(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
