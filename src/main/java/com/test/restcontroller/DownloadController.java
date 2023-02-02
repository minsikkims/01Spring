package com.test.restcontroller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownloadController {

	@GetMapping(value="/ReqDownload",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> func1(String path)throws Exception {
		System.out.println("PATH : " + path);
		
		//FileSysteResource : 파일시스템의 특정 파일로 부터 정보를 읽어온다. 
		Resource resource = new FileSystemResource(path);
		//파일명 추출
		String resourcename = resource.getFilename();
		//헤더정보 수정위함(파일전송용)
		HttpHeaders headers = new HttpHeaders();
		//ISO-8859-1 : 라틴어(특수문자등 깨짐 방지) 
		headers.add("Content-Disposition","attachment; filename="+new String(resourcename.getBytes("UTF-8"),"ISO-8859-1"));
		//리소스,파일정보가포함된 헤더,상태정보를 전달 
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
		
	}
}
