package compraventa.service;

public class Service {

	public ApiResponse get() {
		ApiResponse rsp = new ApiResponse();
		rsp.setMsg("ok");
		return rsp;
	}
	
	
	public static class ApiResponse {
		private String msg;

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}
