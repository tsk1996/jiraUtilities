import com.jira.searchModule.searchIssues;
import com.jira.searchModule.searchSchema;

import java.util.Scanner;

public class wrapperModule {
    public static void main(String[] args) {
        String ticket = "FCSM-480";
        int choice = menu();

        switch (choice) {
            case 1:
                searchIssues.searchTicket(ticket);
                break;
            case 2:
                searchSchema.loadJsonSchema();
                break;
            default:
                System.out.println("Invalid Response");
                System.exit(0);
        }

    }

    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Search Issue Details");
        System.out.println("2 - Load Json Schema");


        selection = input.nextInt();
        input.close();
        return selection;
    }
}
