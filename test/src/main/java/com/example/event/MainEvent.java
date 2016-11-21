package com.example.event;

public abstract class MainEvent {
	
    public static final class UserLoginRequestedEvent {
        private final String email, password;

        public UserLoginRequestedEvent(final String email,
                final String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
    
    public static final class UserLogoutRequestedEvent {
    }

}
