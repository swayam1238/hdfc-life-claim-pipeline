package com.hdfclife.exception;

public class QueueFullException extends PipelineException {
    public QueueFullException(String message) {
        super(message);
    }
}
