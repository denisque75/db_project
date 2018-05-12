package com.epam.denis_telezhenko.universityhelper.core.firebase_callbacks;

import java.util.List;

public interface DataFromDBCallback<T> {

    void newDataFromDB(List<T> data);
}
