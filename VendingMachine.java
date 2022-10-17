package foundation.assignment;

import java.util.Scanner;

class ItemInventory
{
    static String items[] = {"Chocolates","Juice     ","Biscuits  ","Yogurt    ","Doritos   "};
    static int itemsCount[]= {2,0,4,0,1};
    static int itemsCost[]= {40,50,30,20,30};
    static int checkoutItemIndex[]={0,0,0,0,0};
    static int checkoutItemCount[]={0,0,0,0,0};
    static int checkoutTotalItem=0;

    void updateInventory()
    {
        int inputSelection, quantityUpdate;
        Scanner inputQuantity = new Scanner(System.in);
        Scanner userInput = new Scanner(System.in);
        do {
            System.out.println("Select the item, you want to UPDATE...\n");
            showItems();
            System.out.println("Select from the Index Number to UPDATE, PRESS 9 to ABORT...");
            inputSelection = userInput.nextInt();

            if(inputSelection>=0 && inputSelection <items.length)
            {
                System.out.printf("Item Selected: %s\nEnter the Quantity to add:\n",items[inputSelection]);
                quantityUpdate = inputQuantity.nextInt();
                itemsCount[inputSelection] = itemsCount[inputSelection] + quantityUpdate;
                System.out.println("\nItems Updated Successfully...");
                showItems();
            }

            if(inputSelection==9)
            {break;}
        }while(inputSelection!=9);
    }



    void showItems()
    {
        System.out.println("Press button\t ITEMS\t\t PRICE(cash)\t PRICE(card)\t COUNT\n");
        for(int i=0;i<items.length;i++)
        {
            System.out.printf("\t%d\t %s\t Rs%d\t\t Rs%d\t\t %d\n",i, items[i], itemsCost[i], 2*itemsCost[i],  itemsCount[i]);
        }
    }
}

class moneyUpdate extends ItemInventory
{
    void buyProduct()
    {
        int userInput, quantity, userCheckoutInput;
        Scanner checkoutInput = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Scanner quantityInput = new Scanner(System.in);

        do {
            System.out.println("Select the item, you want to buy...\n");
            showItems();
            System.out.println("PRESS 9 to ABORT...");
            userInput = input.nextInt();
            checkoutItemIndex[checkoutTotalItem] = userInput;

            if(userInput>=0 && userInput <items.length)
            {
                checkoutItemIndex[checkoutTotalItem] = userInput;

                System.out.printf("Selected Item: %s \nEnter the QUANTITY: ",items[userInput]);

                quantity = quantityInput.nextInt();

                if(quantity<=itemsCount[userInput])
                {checkoutItemCount[checkoutTotalItem] = quantity;
                    checkoutTotalItem++;
                    System.out.println("Press 1 to ADD more items, 5 to Checkout");
                    userCheckoutInput = checkoutInput.nextInt();
                    if(userCheckoutInput == 1)
                    {
                        continue;}
                    if(userCheckoutInput == 5)
                    {transaction();
                        break;}
                    else
                    {	System.out.println("Enter the valid Input");
                        break;}
                }
                else
                {System.out.println("Inventory doesn't have entered quantity, try again...");
                    continue;}
            }
            if(userInput==9)
            {break;}
            else
            {System.out.println("\nEnter the valid input");}

        }
        while(userInput!=9);
    }



    void transaction( )
    {
        int userInput, balanceReturn;

        Scanner input = new Scanner(System.in);

        System.out.println("\nPress 1 for CASH and 2 for CARD\n"+
                "Press 9 to ABORT\n");
        do {
            userInput = input.nextInt();

            if(userInput == 1)
            {
                balanceReturn = finalBalanceCalculation();
                generateBill( balanceReturn,userInput);
                break;
            }
            if(userInput == 2)
            {
                balanceReturn = finalBalanceCalculation();
                generateBill( balanceReturn,userInput);
                break;
            }
            if(userInput == 9)
            {break;}
            else
            {System.out.println("\nEnter the valid input");}


        }	while(userInput!=9);
    }

    void generateBill(int amount,int transactionTypeInput)
    {
        int inputAmount, changeBal, transactionInput;
        Scanner moneyInput = new Scanner(System.in);
        Scanner checkInput = new Scanner(System.in);


        do {
            System.out.println("Press 1 to INSERT AMOUNT, 9 to ABORT");

            transactionInput = checkInput.nextInt();

            if(transactionInput==1)
            {
                System.out.println("\nSelected Item Details:\n"+
                        "ITEM \tQUANTITY \tPRICE \t");

                for (int i=0; i<checkoutTotalItem;i++)
                {
                    System.out.printf("\n%s \t%d \t%d\n",items[checkoutItemIndex[i]], checkoutItemCount[i],  transactionTypeInput * itemsCost[checkoutItemIndex[i]] ) ;
                }
                System.out.println("\nTotal AMOUNT: \n"+amount);
                System.out.println("\nEnter AMOUNT and Hit Enter to checkout\n");

                inputAmount = moneyInput.nextInt();

                if (inputAmount>=amount)
                {

                    System.out.println("\n\tINVOICE\n"+
                            "ITEM \tQUANTITY \tPRICE \t");
                    for (int i=0; i<checkoutTotalItem;i++)
                    {
                        System.out.printf("\n%s \t%d \t%d\n",items[checkoutItemIndex[i]], checkoutItemCount[i], transactionTypeInput * itemsCost[checkoutItemIndex[i]] ) ;
                    }

                    System.out.println("\nTRANSACTION SUCCESSFULL\n");

                    for (int i=0; i<checkoutTotalItem; i++)
                    {itemsCount[checkoutItemIndex[i]] = itemsCount[checkoutItemIndex[i]] - checkoutItemCount[i];}

                    makeArraysNull();
                    transactionInput=9;
                    break;
                }


                if (inputAmount<amount)
                {System.out.println("Transaction Failed, Inserted Amount is insufficient, try again...");
                    makeArraysNull();
                    break;}

            }

            if(transactionInput==9)
            {break;}


        }while(transactionInput!=9);
    }

    int finalBalanceCalculation()
    {
        int finalAmount=0;
        for (int i=0; i<checkoutTotalItem;i++)
        {
            finalAmount =  finalAmount + itemsCost[checkoutItemIndex[i]] * checkoutItemCount[i];
        }
        return finalAmount;
    }

    void makeArraysNull()
    {
        for (int i=0;i<checkoutItemIndex.length;i++)
        {
            checkoutItemIndex[i] = 0;
            checkoutItemCount[i] = 0;
            checkoutTotalItem=0;
        }
    }

}
public class VendingMachine {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int userInput;
        ItemInventory item = new ItemInventory();
        moneyUpdate money = new moneyUpdate();
        Scanner input = new Scanner(System.in);
        do
        {
            System.out.println("Vending Machine\n"+
                    "Press 0 to EXIT\n"+
                    "Press 1 to BUY Product\n"+
                    "Press 2 to UPDATE inventory\n"+
                    "Press 3 to SHOW inventory\n");

            userInput = input.nextInt();

            switch(userInput)
            {
                case 0: break;
                case 1: {
                    money.buyProduct();
                    continue;
                }
                case 2: {
                    item.updateInventory();
                    continue;
                }
                case 3: {
                    item.showItems();
                    continue;
                }
                default:
                {
                    System.out.println("\nPress the Valid Number\n");
                    continue;
                }

            }
        }
        while(userInput!=0);
        System.out.println("\nEXIT Successfully!!!");

    }
}
