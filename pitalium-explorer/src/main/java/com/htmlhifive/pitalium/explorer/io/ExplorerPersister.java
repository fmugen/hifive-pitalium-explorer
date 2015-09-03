/*
 * Copyright (C) 2015 NS Solutions Corporation, All Rights Reserved.
 */
package com.htmlhifive.pitalium.explorer.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.htmlhifive.pitalium.core.io.Persister;
import com.htmlhifive.pitalium.explorer.entity.Screenshot;
import com.htmlhifive.pitalium.explorer.entity.Target;
import com.htmlhifive.pitalium.explorer.response.TestExecutionResult;

public interface ExplorerPersister extends Persister {

	int defaultPageSize = 20;

	/**
	 * TestExecutionのリストを取得する。 引数のメソッド名、スクリーンショットを含む（like検索）Screenshotを持つ TestExecutionのリストを取得する。
	 * 
	 * @param searchTestMethod メソッド名
	 * @param searchTestScreen スクリーンショット
	 * @param page 表示ページ番号
	 * @param pageSize 1ページあたりの表示数
	 * @return TestExecutionのリスト
	 */
	Page<TestExecutionResult> findTestExecution(String searchTestMethod, String searchTestScreen, int page, int pageSize);

	/**
	 * Screenshotのリストを取得する。 引数のメソッド名、スクリーンショットを含む（like検索）Screenshotのリストを取得する。
	 * 
	 * @param testExecutionId テスト実行ID
	 * @param searchTestMethod メソッド名
	 * @param searchTestScreen スクリーンショット
	 * @return Screenshotのリスト
	 */
	List<Screenshot> findScreenshot(Integer testExecutionId, String searchTestMethod, String searchTestScreen);

	/**
	 * Screenshotを取得する。
	 * 
	 * @param screenshotid スクリーンショットID
	 * @return Screenshot
	 */
	Screenshot getScreenshot(Integer screenshotid);

	/**
	 * Targetを取得する。
	 * 
	 * @param screenshotId スクリーンショットID
	 * @param targetId 比較対象のID
	 * @return Target
	 */
	Target getTarget(Integer screenshotId, Integer targetId);

	/**
	 * 画像ファイルを取得する。
	 * 
	 * @param screenshotId スクリーンショットID
	 * @param targetId 比較対象のID
	 * @return 画像ファイル
	 * @throws IOException
	 */
	File getImage(Integer screenshotId, Integer targetId) throws IOException;

	File searchProcessedImageFile(Integer screenshotId, String algorithm);

	List<Screenshot> findNotProcessedEdge();

	boolean exsitsProcessedImage(Integer screenshotId, String algorithm);

	String getEdgeFileName(Integer screenshotId, String algorithm);

	void saveProcessedImage(Integer screenshotId, String algorithm, String edgeFileName);
}