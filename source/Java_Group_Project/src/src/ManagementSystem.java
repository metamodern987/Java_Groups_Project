/*
Igor Dmitriev
   5.11.2019
   Класс logic.ManagementSystem, описывающий действия добавления студента, изменения параметров
   студента, удаления студентов из группы.
   Используетс шаблон проектирования Singletone. Этот шаблон показывает, как можно использовать
   только один экземпляр объекта: 1)конструктор объявляется private (теперь напрямую создать объект нельзя)
   2)описывается одна статическая переменная такого же класса
   (т.е. для всех объектов класса будет только одна такая переменная)
   3)создается метод (обычно его называют getInstance), который возвращает ссылку на единственный объект.
 */

import java.io.UnsupportedEncodingException;
import java.util.*;

public class ManagementSystem {

    private List<Groups> groups;          //Список групп
    private Collection<Students> students;//Коллекция студентов

    // Для шаблона Singletone статическая переменная
    private static ManagementSystem instance;

    // закрытый конструктор
    private ManagementSystem() {
        loadGroups();
        loadStudents();
    }

    // метод getInstance - проверяет, инициализирована ли статическая
    // переменная (в случае надобности делает это) и возвращает ее
    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }
/*
    // Метод, который вызывается при запуске класса
    public static void main(String[] args) {
        // Этот код позволяет нам перенаправить стандартный вывод в файл, лежащий в папке проекта
        // Т.к. на экран выводится не совсем удобочитаемая кодировка,
        // файл в данном случае нагляднее
        try {
            System.setOut(new PrintStream("out_information.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        logic.ManagementSystem ms = logic.ManagementSystem.getInstance();

        // Просмотр полного списка групп
        printString("Полный список групп");
        printString("*******************");
        List<logic.Groups> allGroups = ms.getGroups();
        for (logic.Groups gi : allGroups) {
            printString(gi);
        }
        printString();

        // Просмотр полного списка студентов
        printString("Полный список студентов");
        printString("***********************");
        Collection<logic.Students> allStudends = ms.getAllStudents();
        for (logic.Students si : allStudends) {
            printString(si);
        }
        printString();

        // Вывод списков студентов по группам
        printString("Список студентов по группам");
        printString("***************************");
        List<logic.Groups> groups = ms.getGroups();
        // Проверяем все группы
        for (logic.Groups gi : groups) {
            printString("---> Группа:" + gi.getNameGroup());
            // Получаем список студентов для конкретной группы
            Collection<logic.Students> students = ms.getStudentsFromGroup(gi, 2006);
            for (logic.Students si : students) {
                printString(si);
            }
        }
        printString();

        // Создадим нового студента и добавим его в список
        logic.Students s = new logic.Students();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setPatronymic("Викторович");
        s.setSurName("Поляков");
        s.setSex('М');
        Calendar c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        printString("Добавление студента:" + s);
        printString("********************");
        ms.insertStudent(s);
        printString("--->> Полный список студентов после добавления");
        allStudents = ms.getAllStudents();
        for (logic.Students si : allStudents) {
            printString(si);
        }
        printString();

        // Изменим данные о студенте - Перебежкин станет у нас Новоперебежкиным
        // Но все остальное будет таким же - создаем студента с таким же ИД
        s = new logic.Students();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setPatronymic("Владимирович");
        s.setSurName("Новоперебежкин");
        s.setSex('М');
        c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        printString("Редактирование данных студента:" + s);
        printString("*******************************");
        ms.updateStudent(s);
        printString("--->> Полный список студентов после редактирования");
        allStudents = ms.getAllStudents();
        for (logic.Students si : allStudents) {
            printString(si);
        }
        printString();

        // Удалим нашего студента
        printString("Удаление студента:" + s);
        printString("******************");
        ms.deleteStudent(s);
        printString("--->> Полный список студентов после удаления");
        allStudents = ms.getAllStudents();
        for (logic.Students si : allStudents) {
            printString(si);
        }
        printString();

        // Здесь мы переводим всех студентов одной группы в другую
        // Мы знаем, что у нас 2 группы
        // Не совсем элегантное решение, но пока сделаем так
        logic.Groups g1 = groups.get(0);
        logic.Groups g2 = groups.get(1);
        printString("Перевод студентов из 1-ой во 2-ю группу");
        printString("***************************************");
        ms.moveStudentsToGroup(g1, 2006, g2, 2007);
        printString("--->> Полный список студентов после перевода");
        allStudents = ms.getAllStudents();
        for (logic.Students si : allStudents) {
            printString(si);
        }
        printString();

        // Удаляем студентов из группы
        printString("Удаление студентов из группы:" + g2 + " в 2006 году");
        printString("*****************************");
        ms.removeStudentsFromGroup(g2, 2006);
        printString("--->> Полный список студентов после удаления");
        allStudents = ms.getAllStudents();
        for (Iterator i = allStudents.iterator(); i.hasNext(); ) {
            printString(i.next());
        }
        printString();
    }
*/
    // Метод создает две группы и помещает их в коллекцию для групп
    public void loadGroups() {
        // Проверяем - может быть наш список еще не создан вообще
        if (groups == null) {
            groups = new ArrayList<Groups>();
        } else {
            groups.clear();
        }
        Groups g = null;

        g = new Groups();
        g.setGroupId(1);
        g.setNameGroup("Первая");
        g.setCurator("Доктор Борменталь");
        g.setSpeciality("Создание собачек из человеков");
        groups.add(g);

        g = new Groups();
        g.setGroupId(2);
        g.setNameGroup("Вторая");
        g.setCurator("Профессор Преображенский");
        g.setSpeciality("Создание человеков из собачек");
        groups.add(g);
    }

    // Метод создает несколько студентов и помещает их в коллекцию
    public void loadStudents() {
        if (students == null) {
            // Мы используем коллекцию, которая автоматически сортирует свои элементы
            students = new TreeSet<Students>();
        } else {
            students.clear();
        }

        Students s = null;
        Calendar c = Calendar.getInstance();

        // Вторая группа
        s = new Students();
        s.setStudentId(1);
        s.setFirstName("Иван");
        s.setPatronymic("Сергеевич");
        s.setSurName("Степанов");
        s.setSex('М');
        c.set(1990, 3, 20);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        s = new Students();
        s.setStudentId(2);
        s.setFirstName("Наталья");
        s.setPatronymic("Андреевна");
        s.setSurName("Чичикова");
        s.setSex('Ж');
        c.set(1990, 6, 10);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        // Первая группа
        s = new Students();
        s.setStudentId(3);
        s.setFirstName("Петр");
        s.setPatronymic("Викторович");
        s.setSurName("Сушкин");
        s.setSex('М');
        c.set(1991, 3, 12);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);

        s = new Students();
        s.setStudentId(4);
        s.setFirstName("Вероника");
        s.setPatronymic("Сергеевна");
        s.setSurName("Ковалева");
        s.setSex('Ж');
        c.set(1991, 7, 19);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);
    }

    // Получить список групп
    public List<Groups> getGroups() {
        return groups;
    }

    // Получить список всех студентов
    public Collection<Students> getAllStudents() {
        return students;
    }

    // Получить список студентов для определенной группы
    public Collection<Students> getStudentsFromGroup(Groups group, int year) {
        Collection<Students> l = new TreeSet<Students>();
        for (Students si : students) {
            if (si.getGroupId() == group.getGroupId() && si.getEducationYear() == year) {
                l.add(si);
            }
        }
        return l;
    }

    // Перевести студентов из одной группы с одним годом обучения в другую группу с другим годом обучения
    public void moveStudentsToGroup(Groups oldGroup, int oldYear, Groups newGroup, int newYear) {
        for (Students si : students) {
            if (si.getGroupId() == oldGroup.getGroupId() && si.getEducationYear() == oldYear) {
                si.setGroupId(newGroup.getGroupId());
                si.setEducationYear(newYear);
            }
        }
    }

    // Удалить всех студентов из определенной группы
    public void removeStudentsFromGroup(Groups group, int year) {
        // Мы создадим новый список студентов БЕЗ тех, кого мы хотим удалить.
        // Возможно не самый интересный вариант. Можно было бы продемонстрировать
        // более элегантный метод, но он требует погрузиться в коллекции более глубоко
        // Здесь мы не ставим себе такую цель
        Collection<Students> tmp = new TreeSet<Students>();
        for (Students si : students) {
            if (si.getGroupId() != group.getGroupId() || si.getEducationYear() != year) {
                tmp.add(si);
            }
        }
        students = tmp;
    }

    // Добавить студента
    public void insertStudent(Students student) {
        // Просто добавляем объект в коллекцию
        students.add(student);
    }

    // Обновить данные о студенте
    public void updateStudent(Students student) {
        // Надо найти нужного студента (по его ИД) и заменить поля
        Students updStudent = null;
        for (Students si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // Вот этот студент - запоминаем его и прекращаем цикл
                updStudent = si;
                break;
            }
        }
        updStudent.setFirstName(student.getFirstName());
        updStudent.setPatronymic(student.getPatronymic());
        updStudent.setSurName(student.getSurName());
        updStudent.setSex(student.getSex());
        updStudent.setDateOfBirth(student.getDateOfBirth());
        updStudent.setGroupId(student.getGroupId());
        updStudent.setEducationYear(student.getEducationYear());
    }

    // Удалить студента
    public void deleteStudent(Students student) {
        // Надо найти нужного студента (по его ИД) и удалить
        Students delStudent = null;
        for (Students si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // Вот этот студент - запоминаем его и прекращаем цикл
                delStudent = si;
                break;
            }
        }
        students.remove(delStudent);
    }

    // Этот код позволяет нам изменить кодировку
    // Такое может произойти если используется IDE - например NetBeans.
    // Тогда вы получаете просто одни вопросы, что крайне неудобно читать
    public static void printString(Object s) {
        //System.out.println(s.toString());
        try {
            System.out.println(new String(s.toString().getBytes("windows-1251"), "windows-1251"));

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    public static void printString() {
        System.out.println();
    }
}
