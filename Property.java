import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

public class Property {
    public static void main(String[] args) throws IOException {
        String userpath = "usersData.txt";
        File users = new File(userpath);
        String propertyPath = "properties.txt";
        File properties = new File(propertyPath);
        String taskpath = "tasks.txt";
        File tasks = new File(taskpath);
        String transact = "transaction.txt";
        File transaction = new File(transact);
        Scanner console = new Scanner(System.in);
        String [] task = {"Task ID","Username","Property ID","Task","Addressed by","Amount","Status"};
        String [] payments = {"Payment ID","Sender","Receiver","Amount","Reason","Status"};
        String [] data = {"Username","Password","Full Name","CNIC","Phone no.","D.O.B.","Address","Role"};
        String [] property = {"Owner","Property ID","Category","Location","Area","Status","Amount"};
        String [] userData = new String[8];
        String ctg;
        boolean execute = true;
        if(!users.exists())
        users.createNewFile();
        if(!tasks.exists())
        tasks.createNewFile();
        if(!properties.exists())
        properties.createNewFile();
        if(!transaction.exists())
        transaction.createNewFile();
        do{
            try{
            System.out.println("-----Property Management System-----");
            startup(data);
                System.out.println();
                System.out.println("Press 1* if you want to create a new account\nPress 2* if you want to login to your account\nPress 0* if you want to close the program");
                System.out.print("Enter: ");
                int decide = console.nextInt();
                console.nextLine();
                if(decide==1){
                    createAccount(data, userData);
                }
            else if(decide==2){
                System.out.println("----Account Login Interface----");
                userData=loginAccount();
                if(userData!=null){     
                    System.out.println("Welcome " + userData[0]);
                    if("Admin".equals(userData[7])){
                        while(true){
                            try{
                                System.out.print("\nPress 1* to create an account\nPress 2* to delete an account\nPress 3* to retrieve user information\nPress 4* to view user's transaction history\nPress 0* to logout from your account\nEnter: ");
                                int option = console.nextInt();
                                console.nextLine();
                                if(option==1)
                                createAccount(data,userData);
                                else if(option==2){
                                    String []save=remove(users, 0, "username", "Account");
                                    remove(properties, 0, save[0], "Property");
                                }
                                else if(option==3){
                                    retrieveUserInfo(data,property,userData[7]);
                                }
                                else if(option==4){
                                    transactionHistory("", payments);
                                }
                                else if(option==0)
                                break;
                                else{
                                    System.out.println("Invalid input");
                                }
                            }
                            catch(RuntimeException e){
                                console.nextLine();
                                System.out.println("Invalid input");
                            }
                        }
                    }
                    else if("Property Dealer".equals(userData[7])){
                        while(true){
                            try{
                                System.out.print("\nPress 1* to add new properties\nPress 2* to remove properties\nPress 3* to view unowned properties\nPress 4* to view owned properties\nPress 5* to search for a property\nPress 6* to view properties owned by the user\nPress 0* to logout from your account\nEnter: ");
                                int option = console.nextInt();
                                console.nextLine();
                                if(option==1){
                                    while(true){
                                        try{
                                            System.out.println();
                                            System.out.print("\n----Add Property Listings----\n\nPress 1* to add plots\nPress 2* to add commercial plots\nPress 3* to add apartments\nPress 4* to add houses\nPress 5* to add agricultural land\nPress 0* to exit\nEnter: ");
                                            int add = console.nextInt();
                                            console.nextLine();
                                            if(add==1){
                                                ctg = "Plots";
                                                addProperty(propertyPath,property, ctg);
                                            }
                                            else if(add==2){
                                                ctg = "Commercial plots";
                                                addProperty(propertyPath,property, ctg);
                                            }
                                            else if(add==3){
                                                ctg = "Apartments";
                                                addProperty(propertyPath,property, ctg);
                                            }
                                            else if(add==4){
                                                ctg = "House";
                                                addProperty(propertyPath, property, ctg);
                                            }
                                            else if(add==5){
                                                ctg = "Land";
                                                addProperty(propertyPath,property, ctg);
                                            }
                                            else if(add==0){
                                                break;
                                            }
                                            else
                                            System.out.println("Invalid input");
                                        }catch(RuntimeException e){
                                            console.nextLine();
                                            System.out.println("Invalid input");
                                        }
                                    }
                                }
                                else if(option==2){
                                    remove(properties, 1, "Property ID", "Property");
                                }
                                else if(option==3){
                                    unownedProps(property);
                                }
                                else if(option==4){
                                    ownedProperty(property);
                                }
                                else if(option==5){
                                    searchProperty(property,"");
                                }
                                else if(option==6){
                                    retrieveUserInfo(data, property, userData[7]);
                                }
                                else if(option==0)
                                break;
                                else
                                System.out.println("Invalid input");
                            }
                            catch(RuntimeException e){
                                console.nextLine();
                                System.out.println("Invalid input");
                            }
                        }
                    }
                    else if("User".equals(userData[7])){
                        while(true){
                            try{
                                System.out.print("\nPress 1* to buy a property\nPress 2* to sell a property\nPress 3* to view your properties\nPress 4* to rent out your property\nPress 5* to remove a property from lease\nPress 6* to rent a property\nPress 7* to send maintenance requests\nPress 8* to view unpaid challan\nPress 9* to pay your property payments\nPress 10* to pay your rental payments\nPress 11* to pay your maintenance payments\nPress 12* to view your transaction history\nPress 0* to logout from your account\nEnter: ");
                                int option = console.nextInt();
                                console.nextLine();
                                if(option==0)
                                break;
                                else if(option==1){
                                    buyProperty(userData,property);
                                }
                                else if(option==2){
                                    convertProperty(properties, property, userData,"Sold","Rent","Available");
                                }
                                else if(option==3){
                                    retrieveProperty(true,userData[0], property, userData[0],0);
                                }
                                else if(option==4){
                                    convertProperty(properties, property, userData, "Sold","","Rent");
                                }
                                else if(option==5){
                                    convertProperty(properties, property, userData, "Rent","Rented","Sold");
                                }
                                else if(option==6){
                                    rentProperty(userData, property);
                                }
                                else if(option==7){
                                    maintainRequest(userData, property, task);
                                }
                                else if(option==8){
                                    readChallan(userData[0], payments);
                                }
                                else if(option==9){
                                    purchase(transaction,properties,userData, payments,"Available","Sold",0,"Buy ");      
                                }
                                else if(option==10){
                                    purchase(transaction, properties, userData, payments, "Rent", "Rented",0,"Rent ");
                                }
                                else if(option==11){
                                    purchase(transaction, properties, userData, payments, "", "", 1,"Maintain ");
                                }
                                else if(option==12){
                                    transactionHistory(userData[0], payments);
                                }
                                else
                                System.out.println("Invalid input");
                            }
                            catch(RuntimeException e){
                                console.nextLine();
                                System.out.println("Invalid input");
                            }
                        }
                    }
                    else if("Maintenance Worker".equals(userData[7])){
                        while(true){
                            try{
                                System.out.print("\nPress 1* to view incomplete maintenance tasks\nPress 2* to update maintenance tasks\nPress 3* to view completed maintenance tasks history\nPress 4* to view maintenance requests\nPress 5* to remove tasks\nPress 6* to receive task\nPress 0* to logout from your account\nEnter: ");
                                int option = console.nextInt();
                                console.nextLine();
                                if(option==0)
                                break;
                                else if(option==1){
                                    retrieveTask(task, "Incomplete", userData[0], 6);
                                }
                                else if(option==2){
                                    updateTask(tasks, task, userData[0]);
                                }
                                else if(option==3){
                                    retrieveTask(task, "Completed", userData[0], 6);
                                }
                                else if(option==4){
                                    retrieveTask(task, "Open","None",6);
                                }
                                else if(option==5){
                                    convertMaintain(tasks, task, userData[0], userData[0], "None", 4, "Incomplete", "Open", 6);
                                }
                                else if(option==6){
                                    convertMaintain(tasks, task, userData[0], "None", userData[0], 4, "Open", "Incomplete", 6);
                                }
                                else
                                System.out.println("Invalid input");
                            }
                            catch(RuntimeException e){
                                console.nextLine();
                                System.out.println("Invalid input");
                            }
                        }
                    }
                    else if("Accountant".equals(userData[7])){
                        while(true){
                            try{
                                System.out.print("\nPress 1* to view user's unpaid challans\nPress 2* to view user's transaction history\nPress 3* to view transactions history\nPress 4* to view unpaid challans\nPress 0* to logout from your account\nEnter: ");
                                int option = console.nextInt();
                                console.nextLine();
                                if(option==0)
                                break;
                                else if(option==1){
                                    readChallan("",payments);
                                }
                                else if(option==2){
                                    transactionHistory("",payments);
                                }
                                else if(option==3){
                                    allTransaction(payments);
                                }
                                else if(option==4){
                                    unPaid(payments);
                                }
                                else
                                System.out.println("Invalid input");
                            }
                            catch(RuntimeException e){
                                console.nextLine();
                                System.out.println("Invalid input");
                            }
                        }
                    }
                    userData = null;
                }               
            } 
            else if(decide==0){
                execute = false;
                System.out.println("Program executed successfully");
            }
            else{
                System.out.println("Invalid input");
            }      
        }
        catch(RuntimeException e){
            console.nextLine();
            System.out.println("Invalid input.");
        }
        }while(execute);     
    }


    public static boolean retrieveTask(String []taskData,String equals,String user,int index){
        String [] line;
        String data;
        boolean pFound = false;
            try(BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))){   
                while((data = (reader.readLine()))!=null){
                    line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
                    if(line[index].equals(equals)&&line[4].equals(user)){
                        System.out.println("--Tasks Information--");
                        pFound = true;
                        for(int i = 0;i<taskData.length;i++){
                            System.out.printf("%-15s:%30s\n",taskData[i],line[i]);
                        }      
                    }
                }
                if(!pFound)
                System.out.println("No tasks found");
            }catch(IOException e){}    
            return pFound;
        }


    public static void updateTask(File file,String []taskData,String user){
        Scanner console = new Scanner(System.in);
        File quick = new File("quick.txt");
        String id;
        boolean found = false;
        if(retrieveTask(taskData, "Incomplete",user,6)){
            System.out.print("Enter task id: ");
            id = console.nextLine();
            String []line;
            String data;
        try(FileWriter writer = new FileWriter("quick.txt",true);
        BufferedReader reader = new BufferedReader(new FileReader(file))){
            while((data = (reader.readLine()))!=null){
                line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
                if(id.equals(line[0])&&user.equals(line[4])&&"Incomplete".equals(line[6])){
                    found = true;
                    line[6]="Completed";
                    generateTaskChallan(line, user, taskData);
                    for(int i = 0;i<line.length;i++){
                        System.out.printf("%-15s:%20s\n",taskData[i],line[i]);
                    }
                    for(int j = 0;j<line.length;j++){
                        writer.write(line[j]+ "$^$$^^$$$^^^");
                    }
                    writer.write("\n");
                }
                else
                writer.write(data + "\n");
            }
            if(!found)
            System.out.println("Invalid task entered");
        }
        catch(IOException e){}
        if(found){
            file.delete();
            quick.renameTo(file);
        }
        else
        quick.delete();
    }
    }





    public static void convertMaintain(File file,String[]taskData,String user,String from1,String to1,int index1,String from2,String to2,int index2){
        Scanner console = new Scanner(System.in);
        File quick = new File("quick.txt");
        String id;
        boolean found = false;
        if(retrieveTask(taskData, from2,from1,6)){
            System.out.print("Enter task id: ");
            id = console.nextLine();
            String []line;
            String data;
        try(FileWriter writer = new FileWriter("quick.txt",true);
        BufferedReader reader = new BufferedReader(new FileReader(file))){    
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if(id.equals(line[0])&&line[index1].equals(from1)&&line[index2].equals(from2)){
                found = true;
                line[index1]=to1;
                line[index2]=to2;
                for(int i = 0;i<line.length;i++){
                    System.out.printf("%-15s:%20s\n",taskData[i],line[i]);
                }
                for(int j = 0;j<line.length;j++){
                    writer.write(line[j]+ "$^$$^^$$$^^^");
                }
                writer.write("\n");
            }
            else
            writer.write(data + "\n");
        }
    }catch(IOException e){}
    if(found){
        file.delete();
        quick.renameTo(file);
        if(from2.equals("Open"))
        System.out.println("Task received");
        else if(from2.equals("Incomplete"))
        System.out.println("Task removed");
        }
        else{
            System.out.println("Invalid task entered");
            quick.delete();
        }
        }
    }




    public static void convertProperty(File properties,String[]propData,String []user,String from1,String from2,String to){
        Scanner console = new Scanner(System.in);
        File quick = new File("quick.txt");
        String id;
        boolean found = false;
        if(retrieveProperty(true,user[0], propData, user[0], 0)){
            System.out.print("Enter property id: ");
            id = console.nextLine();
            String []line;
            String data;
        try(FileWriter writer = new FileWriter("quick.txt",true);
        BufferedReader reader = new BufferedReader(new FileReader("properties.txt"))){   
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if(line[0].equals(user[0])&&(line[5].equals(from1)||line[5].equals(from2))&&id.equals(line[1])){
                found = true;
                line[5]=to;
                for(int i = 0;i<line.length;i++){
                    System.out.printf("%-15s:%20s\n",propData[i],line[i]);
                }
                for(int j = 0;j<line.length;j++){
                    writer.write(line[j]+ "$^$$^^$$$^^^");
                }
                writer.write("\n");
            }
            else
            writer.write(data + "\n");
        }
        writer.close();
        }catch(IOException e){}
        if(found){
            System.out.println("Property converted to "+to);
            properties.delete();
            quick.renameTo(properties);
        }
        else{
            System.out.println("Can't convert property to "+to);
            quick.delete();
        }
        }

    }





    public static void rentProperty(String []user,String[] propData){
        boolean found = false;
        String data;
        Scanner console = new Scanner(System.in);
        String []line;
        try(BufferedReader reader = new BufferedReader(new FileReader("properties.txt"))){   
            System.out.println("---Rental Properties---");
            while((data = (reader.readLine()))!=null){
                line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
                if(line[5].equals("Rent")){
                System.out.println("--Property Information--");
                found = true;
                for(int i = 0;i<line.length;i++){
                    System.out.printf("%-15s:%20s\n",propData[i],line[i]);
                }
            }
        }
        }catch(IOException e){
            System.out.println();
        }
        if(found){
            while(true){
                try{
                    System.out.print("Press 1* if you want to rent a property\nPress 0* if you want to exit\nEnter: ");
                int run = console.nextInt();
                console.nextLine();
                if(run==1){
                    String [][]save = searchProperty(propData,"Rent");
                    if(Boolean.parseBoolean(save[0][0])){
                        if(save[1][0].equals(user[0]))
                        System.out.println("You cannot rent your own property");
                        else{
                            save[1][4]="Rent "+save[1][4];
                            generateChallan(save,user, propData,"Rent ");
                        }
                    }
                }
                else if(run==0){
                    return;
                }
                else
                System.out.println("Invalid input");
            }catch(RuntimeException e){
                console.nextLine();
                System.out.println("Invalid input");
            }
        }
    }
    else
    System.out.println("No rental properties");
    }

    public static void unownedProps(String []propData){
        String []line;
        String data;
        boolean found = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("properties.txt"))){
            System.out.println("---Unowned Properties---");
            while((data = (reader.readLine()))!=null){
                line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
                if("None".equals(line[0])){
                    found = true;
                    System.out.println("--Property Information--");
                for(int i = 0;i<line.length;i++){
                    System.out.printf("%-15s:%20s\n",propData[i],line[i]);
                }
            }
        }
        }
        catch(IOException e){}
        if(!found)
        System.out.println("No unowned properties");
    }




    public static boolean maintainRequest(String []userData,String[]propData,String[]task){
        Scanner console = new Scanner(System.in);
        String [] taskData = new String[7];
        boolean updated = false;
        int count = 0,i;
        System.out.println("---Maintenance Request---\n");      
        System.out.print("Enter Property ID: ");
        String id = console.nextLine();
        if(retrieveProperty(true,userData[0], propData, id, 1)){
            for(i = 1;i<=3;i++){
                System.out.println("--Task Information");
                for(int j = 0;j<task.length;j++){
                    if(j<count)
                    continue;
                    if(count==0){
                        taskData[j]=uniqueID("tasks.txt");
                        count++;
                    }
                    else if(count==1){
                        taskData[j]=userData[0];
                        count++;
                    }
                    else if(count==2){
                        taskData[j]=id;
                        count++;
                    }
                    else if(count==3){
                        System.out.print("Enter task: ");
                        taskData[j]=console.nextLine();
                        count++;
                    }
                    else if(count==4){
                        taskData[j]="None";
                        count++;
                    }
                    else if(count==5){
                        try{
                            System.out.print("Enter payment: ");
                            taskData[j]=Long.toString(console.nextLong())+" Rs. ";
                            console.nextLine();
                            count++;
                        }
                        catch(RuntimeException e){
                            console.nextLine();
                            System.out.println("Invalid input");
                            break;
                        }
                    }
                    else if(count==6){
                        taskData[j]="Open";
                        count++;
                    }
                    System.out.printf("%-15s:%20s\n",task[j],taskData[j]);
                }
            }
            if(count==7){
                System.out.println("\n---Task Information---\n");
                for(int jk = 0;jk<taskData.length;jk++){
                    System.out.printf("%-15s:%20s\n",task[jk],taskData[jk]);
                }
                    appendtoFile("tasks.txt",taskData);
                    updated = true;
            }
            else if(i==4)
            System.out.println("Multiple invalid trials");
            if(updated)
            System.out.println("Request successfully added");
            else if(count==7)
            System.out.println("Error while adding request");
        }
        else
        System.out.println("Property not found");
        return updated;
    }


    public static void readChallan(String user,String[]challanData){
        String []line;
        String data;
        boolean found = false;
        Scanner console = new Scanner(System.in);
        if(user.equals("")){
            System.out.print("Enter username: ");
            user = console.nextLine();
        }
        try(BufferedReader reader = new BufferedReader(new FileReader("transaction.txt"))){   
        System.out.println("---Unpaid Challan---");
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if((user.equals(line[1])&&(line[5].equals("Unpaid")))){
                found = true;
                System.out.println("--Challan Information--");
                for(int i = 0;i<line.length;i++){
                    System.out.printf("%-15s:%20s\n",challanData[i],line[i]);
                }
            }
        }
        }
        catch(IOException e){}
        if(!found)
        System.out.println("No unpaid challans");
    }

    public static void unPaid(String[]challanData){
        String []line;
        String data;
        boolean found = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("transaction.txt"))){    
        System.out.println("---Unpaid Challan---");
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if((line[5].equals("Unpaid"))){
                found = true;
                System.out.println("--Challan Information--");
                for(int i = 0;i<line.length;i++){
                    System.out.printf("%-15s:%20s\n",challanData[i],line[i]);
                }
            }
        }
        }
        catch(IOException e){}
        if(!found)
        System.out.println("No unpaid challan");
    }

    public static void transactionHistory(String user,String[]challanData){
        String []line;
        String data;
        boolean found = false;
        Scanner console = new Scanner(System.in);
        if(user.equals("")){
            System.out.print("Enter username: ");
            user = console.nextLine();
        }
        try(BufferedReader reader = new BufferedReader(new FileReader("transaction.txt"))){
        System.out.println("---Transactions---");
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if((user.equals(line[1])||user.equals(line[2]))&&("Paid".equals(line[5]))){
                found = true;
                System.out.println("--Challan Information--");
                for(int i = 0;i<line.length;i++){
                    System.out.printf("%-15s:%20s\n",challanData[i],line[i]);
                }
            }
        }
        }
        catch(IOException e){}
        if(!found)
        System.out.println("No transactions found");
    }


    public static void allTransaction(String[]challanData){
        String []line;
        String data;
        boolean found = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("transaction.txt"))){
        System.out.println("---Transactions---");
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if(("Paid".equals(line[5]))){
                found = true;
                System.out.println("--Challan Information--");
                for(int i = 0;i<line.length;i++){
                    System.out.printf("%-15s:%20s\n",challanData[i],line[i]);
                }
            }
        }
        }
        catch(IOException e){}
        if(!found)
        System.out.println("No tasks found");
    }


    public static String[][] payChallan(File file,String[]userData,String []challanData,String reason){
        Scanner console = new Scanner(System.in);
        String []line;
        String data;
        String [][] store = new String[2][6]; 
        boolean pay = false,found = false;
        System.out.print("Enter challan no: ");
        String challan = console.nextLine();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if(challan.equals(line[0])&&(userData[0].equals(line[1]))&&(line[5].equals("Unpaid"))&&line[4].contains(reason)){
                found = true;
                System.out.println("--Challan Information--");
                for(int i = 0;i<line.length;i++){
                    System.out.printf("%-15s:%20s\n",challanData[i],line[i]);
                }
                store[1]=line;
                while(true){
                    try{
                        System.out.print("Press 1* to pay challan\nPress anything else to exit\nEnter: ");
                        int find = console.nextInt();
                        console.nextLine();
                        if(find==1){
                            pay = true;
                            System.out.println("Payment completed");
                            break;
                        }
                        else
                        break;
                        
                    }
                    catch(RuntimeException e){
                        console.nextLine();
                        break;
                    }
                }
                break;
            }
        }
    }
    catch(IOException e){}
    if(!found)
    System.out.println("No unpaid challan");
        store[0][0]=Boolean.toString(pay);
        return store;
    }



    public static void purchase(File file,File properties,String[]userData,String []challanData,String from,String to,int maintain,String reason){
        boolean foundChallan = false,foundProp = false;
        String [][] store = payChallan(file,userData, challanData,reason);
        if(Boolean.parseBoolean(store[0][0])){
            String line;
            String[]data;
            File temp = new File("temp.txt");
            try(BufferedReader reader = new BufferedReader(new FileReader(file));
            FileWriter writer = new FileWriter(temp,true)){
            while((line = (reader.readLine()))!=null){
                data = line.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^");
                if(((data[0]).equals(store[1][0]))&&("Unpaid".equals(store[1][5]))){
                    data[5]="Paid";
                    foundChallan = true;
                    for(int i = 0;i<data.length;i++){
                        writer.write(data[i] + "$^$$^^$$$^^^");
                    }
                    writer.write("\n");
                }
                else if(data[4].equals(store[1][4])&&data[5].equals("Unpaid"))
                continue;
                else{
                    writer.write(line + "\n");
                }
            } 
        }
        catch(IOException e){}
        if(maintain==1&&foundChallan){
            file.delete();
            temp.renameTo(file);
            foundChallan=false;
        }
        else if(foundChallan){
            File tempo = new File("tempo.txt");
            try(BufferedReader reader = new BufferedReader(new FileReader(properties));
            FileWriter writer = new FileWriter(tempo,true)){
            while((line = (reader.readLine()))!=null){
                data = line.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^");
                String []down = store[1][4].split(" ");
                if((down[1].equals(data[1]))&&(from.equals(data[5]))){
                    foundProp = true;
                    if(reason.equals("Buy "))
                    data[0]=userData[0];
                    data[5]=to;
                    for(int i = 0;i<data.length;i++){
                        writer.write(data[i] + "$^$$^^$$$^^^");
                    }
                    writer.write("\n");
                }
                else
                writer.write(line + "\n");
            }
        }catch(IOException e){}
        if(foundProp){
            file.delete();
            temp.renameTo(file);
            properties.delete();
            tempo.renameTo(properties);
        }
        else{
            temp.delete();
            tempo.delete();
        }
    }
    else
    temp.delete();
    
    }
}



    public static void buyProperty(String[]user,String []propData){
        String []line;
        String data;
        Scanner console = new Scanner(System.in);
        try(BufferedReader reader = new BufferedReader(new FileReader("properties.txt"))){
        System.out.println("---Available Properties---");
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if("Available".equals(line[5])&&!user[0].equals(line[0])){
                System.out.println("--Property Information--");
                for(int i = 0;i<line.length;i++){
                    System.out.printf("%-15s:%20s\n",propData[i],line[i]);
                }
            }
        }
        }
        catch(IOException e){}
        while(true){
            try{
                System.out.print("Press 1* if you want to buy a property\nPress 0* if you want to exit\nEnter: ");
                int run = console.nextInt();
                console.nextLine();
                if(run==1){
                    String [][] save = searchProperty(propData,"Available");
                    if(Boolean.parseBoolean(save[0][0]))
                    generateChallan(save,user, propData,"Buy ");;
                }
                else if(run==0){
                    return;
                }
                else
                System.out.println("Invalid input");
            }catch(RuntimeException e){
                console.nextLine();
                System.out.println("Invalid input");
            }
        }
    }



    public static void generateChallan(String[][]credit,String[]userData,String [] propData,String reason){
        Scanner console = new Scanner(System.in);
        if(credit[1]!=null){
            while(Boolean.parseBoolean(credit[0][0])){
                try{
                    System.out.print("Press 1* to generate challan\nPress 0* to exit\nEnter: ");
                    int enter = console.nextInt();
                    console.nextLine();
                    if(enter==1){
                        onWayChallan(userData, credit[1],reason);
                        break;
                    }
                    else if(enter==0)
                    break;
                    else
                    System.out.println("Invalid input");
                }
                catch(RuntimeException e){
                    console.nextLine();
                    System.out.println("Invalid input");
                }
            }
        }
    }




    public static void onWayChallan(String []user,String [] credit,String reason){
        String []challan = new String[6];
        challan[0]=uniqueID("transaction.txt");
        challan[1]=user[0];
        if("None".equals(credit[0]))
        challan[2]="Bank";
        else
        challan[2]=credit[0];
        challan[3]=credit[6];
        challan[4]=reason + credit[1];
        challan[5]="Unpaid";
        if(checkUnique("transaction.txt", 4, challan[4],1,user[0])){
                    appendtoFile("transaction.txt",challan);
                    System.out.println("Challan generated");
        }
        else
        System.out.println("Challan already exists");
    }





    public static void generateTaskChallan(String[]credit,String userData,String [] propData){
        Scanner console = new Scanner(System.in);
        if(credit!=null){
            while(true){
                try{
                    System.out.print("Press 1* to generate challan\nPress 0* to exit\nEnter: ");
                    int enter = console.nextInt();
                    console.nextLine();
                    if(enter==1){
                        onWayTaskChallan(userData,credit);
                        break;
                    }
                    else if(enter==0)
                    return;
                    else
                    System.out.println("Invalid input");
                }
                catch(RuntimeException e){
                    console.nextLine();
                    System.out.println("Invalid input");
                }
            }
        }
    }




    public static void onWayTaskChallan(String user,String [] credit){
        String []challan = new String[6];
        
        challan[0]=uniqueID("transaction.txt");
        challan[1]=credit[1];
        challan[2]=credit[4];
        challan[3]=credit[5];
        challan[4]="Maintain "+credit[0];
        challan[5]="Unpaid";
        if(checkUnique("transaction.txt", 4, credit[1],1,user)){
            appendtoFile("transaction.txt",challan);
            System.out.println("Challan generated");
        }
        else
        System.out.println("Challan already exists");
    }


    public static boolean checkUnique(String path,int index,String unique,int index2,String unique2){
        String[]line;
        String data;
        boolean execute = true;
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if(unique.equals(line[index])&&unique2.equals(line[index2])){
                execute = false;
                break;
            }
        }
    }
        catch(IOException e){}
        return execute;
    }


    public static String uniqueID(String path){
        String[]line;
        String data;
        boolean execute = false;
        String id = Long.toString((long)(Math.random()*90000000)+10000000);
        while(true){
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            while((data = (reader.readLine()))!=null){
                line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
                if(id.equals(line[0])){
                    execute = true;
                    break;
                }
            }
            if(!execute){
                reader.close();
                break;
            }
            else{
                id = Long.toString((long)(Math.random()*90000000)+10000000);
            }
        }
        catch(IOException e){}
        }
        return id;
    }
    


    public static boolean retrieveUserInfo(String [] data,String [] propData,String role){  
        String[]line;
        String divideLine;
        boolean ufound = false;
        Scanner console = new Scanner(System.in);
        System.out.print("Enter username: ");
        String user = console.nextLine();
        try(BufferedReader reader = new BufferedReader(new FileReader("usersData.txt"))){
        System.out.println("--User Information--");
        while((divideLine = (reader.readLine()))!=null){
            line = divideLine.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if(user.equals(line[0])){
                ufound = true;
                if(role.equals("Admin")){
                    for(int i = 0;i<line.length;i++){
                        System.out.printf("%-15s:%20s\n",data[i],line[i]);
                    }
                }
                break;
            }
        }
        }
        catch(IOException e){}
        retrieveProperty(ufound,user, propData, user,0);
        return ufound;
    }
    public static boolean retrieveProperty(boolean ufound,String user,String []propData,String credit,int index){
        String [] line;
        String data;
        boolean pFound = false;
        if(ufound){
            try(BufferedReader reader = new BufferedReader(new FileReader("properties.txt"))){
            while((data = (reader.readLine()))!=null){
                line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
                if(credit.equals(line[index])&&user.equals(line[0])){
                    System.out.println("--Property Information--");
                    pFound = true;
                    for(int i = 0;i<propData.length;i++){
                        System.out.printf("%-15s:%30s\n",propData[i],line[i]);
                    }      
                }
            }
            if(!pFound)
            System.out.println("No properties found");
            }catch(IOException e){}    
        }
        else
        System.out.println("No user found");
        return pFound;
    }

    public static String[][] searchProperty(String []propData,String status){
        String [] line;
        String [][]send = new String[2][7];
        boolean pFound = false;
        String id;
        String data;
        Scanner console = new Scanner(System.in);
        System.out.print("Enter Property ID: ");
        id = console.nextLine();
        if(id.matches("([0-9])+")){
            try(BufferedReader reader = new BufferedReader(new FileReader("properties.txt"))){
            while((data = (reader.readLine()))!=null){
                line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
                if(id.equals(line[1])&&(status.equals(line[5])||status.equals(""))){
                    send[1]=line;
                    System.out.println("--Property Information--");
                    pFound = true;
                    for(int i = 0;i<propData.length;i++){
                        System.out.printf("%-15s:%30s\n",propData[i],line[i]);
                    } 
                    break;      
                }
            }
            if(!pFound)
            System.out.println("No property found");
            }catch(IOException e){}    
        }
        else
        System.out.println("Invalid input");
        send[0][0]=Boolean.toString(pFound);
        return send;
    }

    public static void ownedProperty(String []propData){
        String [] line;
        String data;
        boolean pFound = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("properties.txt"))){
        System.out.println("---Owned Properties---");
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if(!"None".equals(line[0])){
                System.out.println("--Property Information--");
                pFound = true;
                for(int i = 0;i<propData.length;i++){
                    System.out.printf("%-15s:%30s\n",propData[i],line[i]);
                } 
            }
        }
        if(!pFound)
        System.out.println("No property found");
    }catch(IOException e){}
    }

    public static String[] remove(File file,int loc,String credential,String delete){
        String line;
        String []save = new String[8];
        String[]data;
        boolean found = false;
        Scanner scan = new Scanner(System.in);
        File temp = new File("temp.txt");
            try(BufferedReader reader = new BufferedReader(new FileReader(file));
            FileWriter writer = new FileWriter("temp.txt");){
                if(!temp.exists())
                temp.createNewFile();
                if(credential.equals("Property ID")||credential.equals("username")){
                    System.out.printf("Enter %s: ",credential);
                    credential = scan.nextLine();
                }
                while((line = (reader.readLine()))!=null){
                    data = line.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^");
                    if(!credential.equals(data[loc])){
                        writer.write(line + "\n");
                    }
                    else{
                        save = data;
                        found = true;
                    }
                }
            }catch(IOException e){}
            if(found){
                file.delete();
                boolean success = temp.renameTo(file);
                if(success)
                    System.out.printf("%s finally deleted\n",delete);
                else
                    System.out.printf("Error while deleting the %s\n",delete);
            }
            else{
                System.out.printf("%s not found\n",delete);
                temp.delete();
            }
        return save;
    }


    public static boolean addProperty(String path,String []property,String ctg){
        Scanner console = new Scanner(System.in);
        String [] add = new String[7];
        boolean updated = false;
        int count = 0,i;
        float area;
        for(i = 1;i<=5;i++){
            for(int j = 0;j<property.length;j++){
                if(j<count)
                continue;
                System.out.println("---Property Info Required---");
                if(j==0){
                    add[j]="None";
                    count++;
            }
                else if(j==1){
                    add[j]=uniqueID(path);
                    count++;
                }
                else if(j==2){
                    add[j]=ctg;
                    count++;
                }
                else if(j==3){
                    System.out.print("Enter location: ");
                    add[j]=console.nextLine();
                    count++;
                }
                else if(j==4){
                    try{
                        System.out.print("Enter the area of the property in marlas: ");
                        area=console.nextInt();
                        console.nextLine();
                        if(area>=18){
                            area=area/18;
                            add[j]=Float.toString(area) + " kanal";
                        }
                        else{
                            add[j]=Float.toString(area) + " marla";
                        }
                        count++;
                    }
                    catch(RuntimeException e){
                        console.nextLine();
                        System.out.println("Invalid input");
                        break;
                    }
                }
                else if(j==5){
                    add[j]="Available";
                    count++;
                }
                else if(j==6){
                    try{
                        System.out.print("Enter the price of the property: ");
                        add[j]="Rs. "+Long.toString(console.nextLong());
                        console.nextLine();
                        count++;
                    }
                    catch(RuntimeException e){
                        console.nextLine();
                        System.out.println("Invalid input");
                        break;
                    }
                }
                System.out.println(property[j]+" : " + add[j]);
            }
        }
        if(count==7){
            System.out.println("\n---Property Information---\n");
                for(int jk = 0;jk<property.length;jk++){
                    System.out.printf("%-15s:%30s\n",property[jk],add[jk]);
                }
                for(int j = 0;j<=5;j++){
                    try{
                        System.out.print("How many properties do you want to add: ");        
                        int a = console.nextInt();
                        console.nextLine();
                        for(int req = 1;req<=a;req++){
                            appendtoFile("properties.txt",add);
                            add[1]=uniqueID(path);
                        }
                        updated = true;
                        break;
                    }catch(RuntimeException e){
                        console.nextLine();
                        System.out.println("Invalid input");}
                }
            }
            else if(i==6){
                System.out.println("Multiple invalid trials...");
            }
        if(updated)
        System.out.println("Properties successfully added");
        else if(count==7)
        System.out.println("Error while adding properties");
        return updated;
    }


    public static boolean enterData(String[]data,String role){
        String[]upload = new String[8];
        int i;
        String cnic,phone,username,password,fullName,address;
        boolean updated = false;
        int count = 0,age = -1;    
        LocalDate date = LocalDate.now();
        Scanner input = new Scanner(System.in);
        for(i = 1;i<=10;i++){
            for(int j = 1;j <= data.length;j++){
                if(j<=count)
                continue;
                System.out.println();
                System.out.println("---Account Information Required---");
                if(count==0){
                    try{
                        System.out.print("Enter your year of birth: ");
                        int birthY = input.nextInt();
                        System.out.print("Enter your month of birth: ");
                        int birthM = input.nextInt();
                        System.out.print("Enter your day of birth: ");
                        int birthD = input.nextInt();
                        LocalDate dob = LocalDate.of(birthY, birthM, birthD);
                        Period duration = Period.between(dob, date);
                        age = duration.getYears();
                        input.nextLine();
                    if(age >= 0 && age<18){
                        throw new ArithmeticException("Age not enough to make an account");
                    }
                    else if(age>=18){
                        upload[5] = dob.toString();
                        count++;
                    }
                    else{
                        System.out.println("Invalid age input");
                        break;
                    }       
                    }
                    catch(ArithmeticException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    
                    catch(RuntimeException e){
                        input.nextLine();
                        System.out.println("Invalid input");
                        break;
                    }    
                }
                else if(count==1){
                    try{
                        System.out.println("The username must start with an alphabet\nThe username may contain alphabets,digits,'.' or '_'");
                        System.out.print("Enter your username: ");
                        username = input.nextLine();
                        if(username.length()>=5 && username.length()<=15&&Character.isLetter(username.charAt(0))&&username.matches("([a-zA-Z0-9._])+")){
                            if(credentialCheck("Username",username,0,role)){
                                upload[0] = username;
                                count++;
                            }
                        }
                        else{
                            System.out.println("Invalid username");
                            break;
                        }
                    }
                    catch(RuntimeException b){
                        System.out.println("Invalid input");
                        break;
                    }
                }
                else if(count==2){
                    try{   
                        System.out.print("Enter your Full Name: ");
                        fullName = input.nextLine();
                        if(fullName.matches("^([A-Za-z ])+$")){
                            upload[2] = fullName;
                            count++;
                        }
                        else{
                            System.out.println("Invalid name");
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println("Invalid input");
                        break;
                    }
                }
                else if(count==3){
                    try{
                        System.out.print("Enter your CNIC: ");
                        cnic = Long.toString(input.nextLong());
                        input.nextLine();
                        if(cnic.length()==13){
                            cnic = String.join("-", cnic.substring(0, 5),cnic.substring(5, 12),cnic.substring(12));
                            if(credentialCheck("cnic",cnic,3,role)){
                                upload[3] = cnic;
                                count++;
                            }
                            else
                            break;
                        }
                        else{
                            System.out.println("Invalid CNIC");
                            break;
                        }
                    }
                    catch(RuntimeException e){
                        input.nextLine();
                        System.out.println("Invalid input");
                        break;
                    }
                }
                else if(count==4){
                    try{
                        System.out.print("Enter your phone no.: ");
                        phone = input.nextLine();
                        if(phone.matches("([0-9])+")&&phone.length()==11&&phone.charAt(0)=='0'){
                            phone = String.join("-", phone.substring(0, 4),phone.substring(4, 10));
                            if(credentialCheck("phone no.",phone,4,role)){
                                upload[4] = phone;
                                count++;
                            }
                            else
                            break;
                        }
                        else{
                            System.out.println("Invalid phone no.");
                        }
                    }
                    catch(RuntimeException f){
                        System.out.println("Invalid input");
                        break;
                    }
                }
                else if(count==5){
                    try{
                        System.out.print("Enter your address: ");
                        address = input.nextLine();
                        upload[6] = address;
                        count++;
                        continue;
                    }
                    catch(RuntimeException f){
                        System.out.println("Invalid address");
                    }
                }
                else if(count==6){
                    try{
                        System.out.print("Enter your password: ");
                        password = input.nextLine();
                        if(password.length()>=8){
                            upload[1] = password;
                            count++;
                        }
                        else{
                            System.out.println("Password too weak");
                            break;
                        }
                    }
                    catch(RuntimeException g){
                        System.out.println("Invalid input");
                        break;
                    }
                }
                else if(count==7){
                    System.out.println("Role: " + role);
                    upload[7] = role;
                    count++;
                }                  
            }
            if(age >= 0 && age<18){
                break;
            }
        }     
            if(count==8){
                System.out.println("\n---Account Information---\n");
                for(int jk = 0;jk<data.length;jk++){
                    System.out.printf("%-15s:%20s\n",data[jk],upload[jk]);
                }
                    appendtoFile("usersData.txt",upload);
                    updated = true;
            }
            else if(i==11)
            System.out.println("Multiple invalid trials");
        if(updated)
        System.out.println("Account successfully created");
        else if(count==8)
        System.out.println("Issue while creating an account");
        return updated;
    }
    public static void appendtoFile(String path,String [] transfer){
            try(FileWriter update = new FileWriter(path,true))
            {
                for(int i = 0;i<transfer.length;i++){
                    update.write(transfer[i] + "$^$$^^$$$^^^");
                }
                update.write("\n");
                update.close();
            }catch(IOException e){}
    }
    public static void startup(String[]data) throws IOException{
        while(true){
            if(findRoles("Admin")){
                System.out.println();
                System.out.println("Welcome.\nAdmin info required...");
                String role = "Admin";
                if(enterData(data, role)){
                    break;
                }
            }
            else{
                break;
            }
        }
    }
    public static boolean credentialCheck(String value,String credential,int n,String role){
        boolean flag = true;
        String data;
        String line[];
            try(BufferedReader a = new BufferedReader(new FileReader("usersData.txt"))){
            while((data = (a.readLine()))!=null){
                line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
                if(credential.equals(line[n])){
                    if(value.equals("Username")){
                        System.out.printf("This %s is already being used in some other account\n", value);
                        flag = false;
                        break;
                    }
                    else if(role.equals(line[7])){
                        System.out.printf("This %s is already being used in some other account\n", value);
                        flag = false;
                        break;
                    }                       
                }
        }
    }
    catch(IOException e){}
        if(flag){
            System.out.printf("Available %s\n",value);
        }
        return flag;
    }

    

    
    public static boolean findRoles(String role){
    String []line;
    String data;
    boolean found = true;
    try(BufferedReader reader = new BufferedReader(new FileReader("usersData.txt"))){
        while((data = (reader.readLine()))!=null){
            line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);
            if(role.equals(line[7])){
                found = false;
            }
        }
    }
    catch(IOException e){}
    return found;
    }
    
    public static void createAccount(String []data,String[]userData){
        Scanner input = new Scanner(System.in);
        String role;
        while(true){
            try{
            System.out.print("\n----Account Creation Interface----\n\n** You should be atleast 18 years old\nPress 1* if you want to create a Property Dealer account\nPress 2* if you want to create a User account\nPress 3* if you want to create a Maintenance Worker account\nPress 4* if you want to create an Accountant account\nPress 5* if you want to create an Admin account\n(Note:This choice is only accessible if you are an admin)\nPress 0* if you want to exit the account creation interface\nEnter: ");
            int choose = input.nextInt();
            input.nextLine();
            if(choose == 1){
                role = "Property Dealer";
                enterData(data, role);
            }
            else if(choose == 2){
                role = "User";
                enterData(data, role);
            }
            else if(choose == 3){
                role = "Maintenance Worker";
                enterData(data, role);
            }
            else if(choose == 4){
                role = "Accountant";
                enterData(data, role);
            }
            else if(choose == 5 && "Admin".equals(userData[7])){
                role = "Admin";
                enterData(data, role);
            }
            else if(choose == 0){
                break;
            }
            else if(choose==5){
                System.out.println("Access denied");
            }
            else
            System.out.println("Invalid input");
        }catch(RuntimeException e){
            input.nextLine();
            System.out.println("Invalid input");
        }
        }
    }


    public static String[] loginAccount() {
        String data;
        Scanner input = new Scanner(System.in);
        System.out.println();
        boolean found = false;
        System.out.print("Enter your username: ");
        String username = input.nextLine();
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        String line[] = new String[8];
        try(BufferedReader b = new BufferedReader(new FileReader("usersData.txt"))){    
            while((data = (b.readLine()))!=null){ 
                line = data.split("\\$\\^\\$\\$\\^\\^\\$\\$\\$\\^\\^\\^", 0);  
                if((username.equals(line[0]))&&(password.equals(line[1]))){  
                    System.out.println("Login successful");
                    found = true;
                    break;
                }
            }
        }catch(IOException e){}
        if(!found){
            System.out.println("Invalid username or password");
            line = null;
        }
        return line;
    }
}