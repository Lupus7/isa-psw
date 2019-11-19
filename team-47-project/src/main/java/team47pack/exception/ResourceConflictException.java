package team47pack.exception;

public class ResourceConflictException extends RuntimeException {

	private Long resourceId;

	public ResourceConflictException(Long resourceId, String message) {
		super(message);
		this.setResourceId(resourceId);
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}