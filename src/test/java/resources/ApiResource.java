package resources;

public enum ApiResource {
	

	getUserAPI("/api/users?page=2"),
	registerUserAPI("/api/register");
	private String resource;
	
	ApiResource(String resource){
		this.resource = resource;		
	}
	
	public String getResource() {
		return resource;
	}

}
