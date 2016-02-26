package com.siims.szb.utils;

/*
 * author:罗贵木
 * time:2015-08-28
 * descption:Base中的各种基础组件回馈信息的包装类
 * version:1.0.0
 */
public class ResultFacade {


	/*
	 * author:罗贵木
	 * time:2015-08-28
	 * descption:result表示这次信息交互是否成功,success表示成功, error表示失败
	 */
	private ResultType result;
	
	/*
	 * author:罗贵木
	 * time:2015-08-28
	 * descption:detail表示这次回馈消息的详细信息
	 */
	private String detail;
	
	/*
	 * author:罗贵木
	 * time:2015-08-28
	 * descption:ResultFacade的无参构造函数
	 */
	public ResultFacade() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * author:罗贵木
	 * time:2015-08-28
	 * descption:ResultFacade的有参数构造函数
	 */
	public ResultFacade(ResultType result, String detail) {
		super();
		this.result = result;
		this.detail = detail;
	}

	public ResultType getResult() {
		return result;
	}

	public void setResult(ResultType result) {
		this.result = result;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}























