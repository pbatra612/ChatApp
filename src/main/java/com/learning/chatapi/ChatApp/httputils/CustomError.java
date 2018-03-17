package com.learning.chatapi.ChatApp.httputils;

public class CustomError {

        private String errorMessage;

        public CustomError(String errorMessage){
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
