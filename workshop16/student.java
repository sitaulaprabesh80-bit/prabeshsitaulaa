package workshop16;


/**
 * Write a description of class student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class student
{
    private String name;
    private int age;
    private String school;
    private int grade;
    private String citizenship;
    private String phone;

    public student(String name, int age, String school, int grade, String citizenship, String phone)
    {
        this.name = name;
        this.age = age;
        this.school = school;
        this.grade = grade;
        this.citizenship = citizenship;
        this.phone = phone;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public String getSchool()
    {
        return school;
    }

    public int getGrade()
    {
        return grade;
    }

    public String getCitizenship()
    {
        return citizenship;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getNameInitials()
    {
        String[] parts = name.trim().split("\\s+");
        String initials = "";
        for (int i = 0; i < parts.length; i++)
        {
            if (parts[i].length() > 0)
            {
                initials = initials + parts[i].substring(0, 1).toUpperCase() + ".";
            }
        }
        return initials;
    }

    public String toString()
    {
        return String.format("%s | %d | Grade %d | %s", name, age, grade, school);
    }
}