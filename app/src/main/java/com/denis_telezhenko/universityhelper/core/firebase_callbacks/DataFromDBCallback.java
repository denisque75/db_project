package com.denis_telezhenko.universityhelper.core.firebase_callbacks;

import java.util.List;

public interface DataFromDBCallback<T> {

    void newDataFromServer(List<T> data);
}
