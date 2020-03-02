package com.test.boar.task;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.test.boar.service.BoardService;

/* 
 * 1. pom.xml 추가
 * 
 * <!--스케쥴러  https://mvnrepository.com/artifact/org.quartz-scheduler/quartz -->
		<dependency>
			<groupId>org.quartz-scheduler</groupId>
			<artifactId>quartz</artifactId>
			<version>2.3.0</version>
		</dependency>


		<!--스케쥴러  https://mvnrepository.com/artifact/org.quartz-scheduler/quartz-jobs -->
		<dependency>
			<groupId>org.quartz-scheduler</groupId>
			<artifactId>quartz-jobs</artifactId>
			<version>2.3.0</version>
		</dependency>

 * 
 * 
 * 
 * 
 * 
 * 2. servlet-context.xml 에 추가
 * 
 * 
 * <task:annotation-driven />
	
	주기적인 작업이 있을 때 @Scheduled 애노테이션을 사용하면 쉽게 적용할 수 있습니다.
	 @Scheduled(fixedDelay=1000) 1초간격으로 무한 반복-->
	 
	 
	 3. DeleteFileTask 클래스 @service 등록
	 
 * */

@Service
public class DeleteFileTask {

	@Value("${savefoldername}")
	private String saveFolder;

	@Autowired
	private BoardService boardService;


	// cron 사용법
	// seconds(초:0~59) minute(분:0~59) hours(시:0~23) day(일:1~31)
	/*
	 * month(1~12) day of week(요일:0~6) year(optional) 초 분 시 일 달 요일
	 * 
	 */

	@Scheduled(cron = "30 * * * * *")
	public void checkFiles() throws Exception {

		// 1.deleteFile 테이블의 String 리스트 가지고 오기
		List<String> list = boardService.getdeleteList();

		for (int i = 0; i < list.size(); i++) {
			File file = new File(saveFolder + list.get(i));

			// 파일 있는지 체크 후 있으면 파일 삭제
			if (file.exists()) {
				if (file.delete()) {
					System.out.println("파일삭제 성공 : "+saveFolder + list.get(i));
				} 
			} 

		}

		boardService.deleteList();

	}
}