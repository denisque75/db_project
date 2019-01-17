package com.denis_telezhenko.universityhelper.ui;

import com.denis_telezhenko.universityhelper.core.entity.Note;
import com.denis_telezhenko.universityhelper.ui.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StubUtils {

    private StubUtils() {}

    public static List<Note> getNotes(){
        List<Note> noteEntities = new ArrayList<>();

        Note n = new Note();
        n.setId(1);
        n.setTitle("Create DB");
        n.setDescription("Кроме того, ресурс даёт возможность подготовиться к зарубежной" +
                " командировке или туристической поездке." +
                " Чтобы получить желаемый результат, необходимо лишь вставить исходную статью в" +
                " соответствующее отделение, затем выбрать параметры операции и активировать процесс.\n" +
                " Мы представляем самый лучший правильный переводчик, работающий с" +
                " самыми различными тематическими текстами (юридическими, медицинскими, " +
                "техническими и прочими).");
        n.setDate(new Date(TimeUtils.getTimeInLong("2018-03-23, 12:45 PM")));
        noteEntities.add(n);

        n = new Note();
        n.setId(2);
        n.setTitle("Go to 3rd floor");
        n.setDescription("Кроме того, ресурс даёт возможность подготовиться к зарубежной" +
                " командировке или туристической поездке." +
                " Чтобы получить желаемый результат, необходимо лишь вставить исходную статью в" +
                " соответствующее отделение, затем выбрать параметры операции и активировать процесс.\n" +
                " Мы представляем самый лучший правильный переводчик, работающий с" +
                " самыми различными тематическими текстами (юридическими, медицинскими, " +
                "техническими и прочими).");
        n.setDate(new Date(TimeUtils.getTimeInLong("2018-03-23, 13:45 PM")));
        noteEntities.add(n);

        n = new Note();
        n.setId(3);
        n.setTitle("Pолучить желаемый результат, необходимо лишь");
        n.setDescription("Кроме того, ресурс даёт возможность подготовиться к зарубежной" +
                " командировке или туристической поездке." +
                " Чтобы получить желаемый результат, необходимо лишь вставить исходную статью в" +
                " соответствующее отделение, затем выбрать параметры операции и активировать процесс.\n" +
                " Мы представляем самый лучший правильный переводчик, работающий с" +
                " самыми различными тематическими текстами (юридическими, медицинскими, " +
                "техническими и прочими).");
        n.setDate(new Date(TimeUtils.getTimeInLong("2018-03-23, 13:45 PM")));
        noteEntities.add(n);

        n = new Note();
        n.setId(4);
        n.setTitle("3rd floor");
        n.setDescription("Кроме того, ресурс даёт возможность подготовиться к зарубежной" +
                " командировке или туристической поездке.");
        n.setDate(new Date(TimeUtils.getTimeInLong("2018-03-23, 13:45 PM")));
        noteEntities.add(n);

        n = new Note();
        n.setId(5);
        n.setTitle("Tематическими текстам");
        n.setDescription("Кроме того, ресурс даёт возможность подготовиться к зарубежной" +
                " командировке или туристической поездке." +
                " Чтобы получить желаемый результат, необходимо лишь вставить исходную статью в" +
                " соответствующее отделение, затем выбрать параметры операции и активировать процесс.\n" +
                " Мы представляем самый лучший правильный переводчик, работающий с" +
                " самыми различными тематическими текстами (юридическими, медицинскими, " +
                "техническими и прочими)." + "Кроме того, ресурс даёт возможность подготовиться к зарубежной" +
                " командировке или туристической поездке." +
                " Чтобы получить желаемый результат, необходимо лишь вставить исходную статью в" +
                " соответствующее отделение, затем выбрать параметры операции и активировать процесс.\n" +
                " Мы представляем самый лучший правильный переводчик, работающий с" +
                " самыми различными тематическими текстами (юридическими, медицинскими, " +
                "техническими и прочими)." + "Кроме того, ресурс даёт возможность подготовиться к зарубежной" +
                " командировке или туристической поездке." +
                " Чтобы получить желаемый результат, необходимо лишь вставить исходную статью в" +
                " соответствующее отделение, затем выбрать параметры операции и активировать процесс.\n" +
                " Мы представляем самый лучший правильный переводчик, работающий с" +
                " самыми различными тематическими текстами (юридическими, медицинскими, " +
                "техническими и прочими).");
        n.setDate(new Date(TimeUtils.getTimeInLong("2018-03-23, 13:45 PM")));
        noteEntities.add(n);

        return noteEntities;
    }


}