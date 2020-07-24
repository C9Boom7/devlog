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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devlog.dto.Portfolio;
import com.ssafy.devlog.service.PortfolioService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping(value="/api")
public class PortfolioController {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PortfolioService portfolioService;
	
	/* 	비로그인 (seq_user==0) : 공개범위가 '전체'인 포트폴리오 반환
	 * 	   로그인 (seq_user!=0) : 공개범위가 '전체'인 포트폴리오와 '이웃공개/비공개'인 포트폴리오의 접근여부 판별하여 반환  */
	
	
	/* show blog portfolio */
	@ApiOperation(value = "블로그 메인에서 모든 포트폴리오를 반환.", response = List.class)    
	@GetMapping(value = "/blog/portfolio/{seq_blog}/{seq_user}")
	public ResponseEntity<List<Portfolio>> selectAllPortfolioByBlog(@PathVariable int seq_blog, @PathVariable int seq_user) {
		logger.debug("selectAllPortfolioByBlog - 호출");
		return new ResponseEntity<List<Portfolio>>(portfolioService.selectAllPortfolioByBlog(seq_user, seq_blog), HttpStatus.OK);
	}
	

	/* basic portfolio crud */
	@ApiOperation(value = "글번호에 해당하는 포트폴리오를 반환", response = String.class)
	@GetMapping(value = "/portfolio/{seq}")
	public ResponseEntity<Portfolio> selectPortfolio(@PathVariable int seq) {
		logger.debug("selectPortfolio - 호출");
		return new ResponseEntity<Portfolio>(portfolioService.selectPortfolio(seq), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "새로운  포트폴리오 입력 ( seq_blog, title, disclosure, summary, start_date, role ) 필수  ", response = String.class)
	@PostMapping(value = "/portfolio")
	public ResponseEntity<String> insertPortfolio(@RequestBody Portfolio portfolio) {
		
		logger.debug("insertPortfolio - 호출");
		
		// insertPortfolio 이후 portfolioBasic 객체에 seq 받아오기 위한 작업
		Portfolio pf = new Portfolio();
		pf = portfolio;
		portfolioService.insertPost(pf);
		
		if (portfolioService.insertPostPortfolio(pf)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = " 포트폴리오 수정", response = String.class)
	@PutMapping(value = "/portfolio")
	public ResponseEntity<String> updatePortfolio(@RequestBody Portfolio portfolio) {
		logger.debug("updatePortfolio - 호출");
		if (portfolioService.updatePost(portfolio)==1&&portfolioService.updatePostPortfolio(portfolio)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 포트폴리오 삭제", response = String.class)
	@DeleteMapping(value = "/portfolio/{seq}")
	public ResponseEntity<String> deletePortfolio(@PathVariable int seq) {
		logger.debug("deletePortfolio - 호출");
		if (portfolioService.deletePortfolio(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}	

}
