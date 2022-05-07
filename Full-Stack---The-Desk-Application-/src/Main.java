import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String[] arr = {"1. I wish to review my expenditure",
            "2. I wish to add my expenditure",
            "3. I wish to delete my expenditure",
            "4. I wish to sort the expenditures",
            "5. I wish to search for a particular expenditure",
            "6. Close the application"
    };
    static ArrayList<Integer> expenses = new ArrayList<Integer>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /*System.out.println("Hello World!");*/
        System.out.println("\n**************************************\n");
        System.out.println("\tWelcome to TheDesk \n");
        System.out.println("**************************************");
        init();
        int  options = -1;
        while (true) {
	        options =  optionsSelection();
	        switch (options){
	        case 1:
	            System.out.println("Your saved expenses are listed below: \n");
	            System.out.println(expenses+"\n");
	            break;
	        case 2:
	            System.out.println("Enter the value to add your Expense: \n");
	            int value = sc.nextInt();
	            expenses.add(value);
	            System.out.println("Your value is updated\n");
	            System.out.println(expenses+"\n");
	
	            break;
	        case 3:
	            System.out.println("You are about the delete all your expenses! \nConfirm again by selecting the same option...\n");
	            int con_choice = sc.nextInt();
	            if(con_choice==options){
	                   expenses.clear();
	                System.out.println(expenses+"\n");
	                System.out.println("All your expenses are erased!\n");
	            } else {
	                System.out.println("Oops... try again!");
	            }
	            break;
	        case 4:
	            sortExpenses(expenses);
	            break;
	        case 5:
	            searchExpenses(expenses);
	            break;
	        case 6:
	            closeApp();
	            break;
	        default:
	            System.out.println("You have made an invalid choice!");
	            break;
	    	}
        }
    }

    private static void init() {
        expenses.add(1000);
        expenses.add(2300);
        expenses.add(45000);
        expenses.add(32000);
        expenses.add(110);
		
	}
	private static int optionsSelection() {
        for(int i=0; i<arr.length;i++){
            System.out.println(arr[i]);
            // display the all the Strings mentioned in the String array
        }
        System.out.print("\nEnter your choice:\t");
        return  sc.nextInt();

    }
    private static void closeApp() {
        System.out.println("Closing your application... \nThank you!");
        System.exit(0);
    }
    private static void searchExpenses(ArrayList<Integer> arrayList) {
        int leng = arrayList.size();
        System.out.println("Enter the expense you need to search:\t");
        int search = sc.nextInt();
        if (arrayList.contains(search))
        	System.out.println("Expense found.");
        else
        	System.out.println("Expense not found!");
    }
    private static void sortExpenses(ArrayList<Integer> arrayList) {
        int arrlength =  arrayList.size();
        arrayList.sort(null);
    }
}
