package com.denis_telezhenko.universityhelper.core.services.firebase;

public class Factory {
    public static final String DELETE_AS_USER = "asUser";
    public static final String DELETE_AS_ADMIN = "asAdmin";

    public static DeleteStrategy deleteStrategy(String strategy) {
        switch (strategy) {
            case DELETE_AS_ADMIN :
                return new DeleteAsAdmin();
            case DELETE_AS_USER :
                return new DeleteAsUser();
        }
        return null;
    }
}
