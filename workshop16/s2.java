package workshop16;


/**
 * Write a description of class Report here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Report
{
    public static void main(String[] args)
    {
        String[] csvData = {
            "Name,Age,School,Grade,Citizenship,Phone",
            "Ram Bahadur Thapa,18,Itahari Int. College,12,01-0012345,9841234567",
            "Sita Kumari Rai,17,Itahari Int. College,11,01-0054321,9807654321",
            "Hari Prasad Sharma,19,ICK College,12,02-0098765,9812345678",
            "Gita Devi Tamang,16,ICP College,10,03-0011111,9800000000",
            "Bikash Kumar Limbu,18,Itahari Int. College,12,01-0077777,9841111111",
            "Anita Rai,17,ICK College,11,04-002222,981234",
            "Deepak Shah,20,ICP College,12,01-00ABCDE,9867890123"
        };

        student[] students = CensusProcessor.parseCSV(csvData);

        for (int i = 0; i < students.length; i++)
        {
            System.out.println(students[i]);
        }

        System.out.println();
        System.out.println(CensusProcessor.generateCensusReport(students));
    }
}
