package workshop16;
import java.util.ArrayList;


/**
 * Write a description of class s3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CensusProcessor
{
    public static student[] parseCSV(String[] csvData)
    {
        student[] students = new student[csvData.length - 1];

        for (int i = 1; i < csvData.length; i++)
        {
            String line = csvData[i];
            String[] parts = line.split(",");

            String name = parts[0].trim();
            int age = Integer.parseInt(parts[1].trim());
            String school = parts[2].trim();
            int grade = Integer.parseInt(parts[3].trim());
            String citizenship = parts[4].trim();
            String phone = parts[5].trim();

            students[i - 1] = new student(name, age, school, grade, citizenship, phone);
        }

        return students;
    }

    public static boolean isValidCitizenship(String cit)
    {
        if (cit.length() != 10)
        {
            return false;
        }

        if (cit.charAt(2) != '-')
        {
            return false;
        }

        for (int i = 0; i < 2; i++)
        {
            if (!Character.isDigit(cit.charAt(i)))
            {
                return false;
            }
        }

        for (int i = 3; i < 10; i++)
        {
            if (!Character.isDigit(cit.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidPhone(String phone)
    {
        return phone.matches("^(97|98)\\d{8}$");
    }

    public static student[] findBySchool(student[] students, String school)
    {
        ArrayList<student> list = new ArrayList<student>();
        for (int i = 0; i < students.length; i++)
        {
            if (students[i].getSchool().equalsIgnoreCase(school))
            {
                list.add(students[i]);
            }
        }

        student[] result = new student[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            result[i] = list.get(i);
        }
        return result;
    }

    public static int countByGrade(student[] students, int grade)
    {
        int count = 0;
        for (int i = 0; i < students.length; i++)
        {
            if (students[i].getGrade() == grade)
            {
                count++;
            }
        }
        return count;
    }

    public static double averageAge(student[] students)
    {
        if (students.length == 0)
        {
            return 0.0;
        }

        int sum = 0;
        for (int i = 0; i < students.length; i++)
        {
            sum = sum + students[i].getAge();
        }

        return (double) sum / students.length;
    }

    public static student findLongestName(student[] students)
    {
        if (students.length == 0)
        {
            return null;
        }

        student longest = students[0];
        for (int i = 1; i < students.length; i++)
        {
            if (students[i].getName().length() > longest.getName().length())
            {
                longest = students[i];
            }
        }

        return longest;
    }

    public static String generateCensusReport(student[] students)
    {
        int total = students.length;
        int valid = 0;
        int invalid = 0;

        for (int i = 0; i < students.length; i++)
        {
            boolean okCit = isValidCitizenship(students[i].getCitizenship());
            boolean okPhone = isValidPhone(students[i].getPhone());

            if (okCit && okPhone)
            {
                valid++;
            }
            else
            {
                invalid++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("==============================\n");
        sb.append("       STUDENT CENSUS REPORT\n");
        sb.append("==============================\n");
        sb.append(String.format("Total: %d | Valid: %d | Invalid: %d\n\n", total, valid, invalid));

        sb.append(String.format("%-6s %-22s %-4s %-7s %-6s %-8s %-8s\n", "Init", "Name", "Age", "Grade", "Cit", "Phone", "Status"));
        sb.append("------------------------------------------------------------------\n");

        for (int i = 0; i < students.length; i++)
        {
            student s = students[i];
            boolean okCit = isValidCitizenship(s.getCitizenship());
            boolean okPhone = isValidPhone(s.getPhone());
            String status = (okCit && okPhone) ? "Valid" : "Invalid";
            sb.append(String.format("%-6s %-22s %-4d %-7d %-6s %-8s %-8s\n",
                    s.getNameInitials(),
                    s.getName(),
                    s.getAge(),
                    s.getGrade(),
                    okCit ? "OK" : "BAD",
                    okPhone ? "OK" : "BAD",
                    status));
        }

        sb.append("\n");
        sb.append("Statistics\n");
        sb.append("----------\n");
        sb.append(String.format("Average age: %.2f\n", averageAge(students)));

        student longest = findLongestName(students);
        if (longest != null)
        {
            sb.append("Longest name: " + longest.getName() + "\n");
        }

        sb.append("\nCounts per school\n");
        sb.append("-----------------\n");

        String[] schools = new String[students.length];
        int schoolCount = 0;

        for (int i = 0; i < students.length; i++)
        {
            String sch = students[i].getSchool();
            boolean found = false;
            for (int j = 0; j < schoolCount; j++)
            {
                if (schools[j].equalsIgnoreCase(sch))
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                schools[schoolCount] = sch;
                schoolCount++;
            }
        }

        for (int i = 0; i < schoolCount; i++)
        {
            int c = 0;
            for (int j = 0; j < students.length; j++)
            {
                if (students[j].getSchool().equalsIgnoreCase(schools[i]))
                {
                    c++;
                }
            }
            sb.append(String.format("%s: %d\n", schools[i], c));
        }

        return sb.toString();
    }

}
