package com.example.demo.common.web;

import org.springframework.http.HttpStatus;

import com.example.demo.common.dto.AbstractValueObject;

/**
 * 封装响应数据和状态 的 对象
 * @author Administrator
 *
 * @param <T>
 */
public class ResponseEntity<T> extends AbstractValueObject 
{
	private static final long serialVersionUID = -8626097827207041927L;
	
	private int status;
    private boolean success = true;
    private T data;

  
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseEntity() {
    }

    public ResponseEntity(boolean success) {
        this.success = success;
    }

    public ResponseEntity(T data) {
        this.data = data;
    }

    public ResponseEntity(int status) {
        this.status = status;
    }

    public ResponseEntity(T data, HttpStatus status) {
        this.data = data;
        this.status = status.value();
    }

    public static ResponseEntity ofFailed() {
        return new ResponseEntity(false);
    }

    public static ResponseEntity ofSuccess() {
        return new ResponseEntity(true);
    }

    public ResponseEntity success(boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResponseEntity data(T data) {
        this.setData(data);
        return this;
    }

    public ResponseEntity status(HttpStatus status) {
        this.setStatus(status.value());
        return this;
    }
}
