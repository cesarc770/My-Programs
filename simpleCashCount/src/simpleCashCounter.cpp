#include <iostream>
using namespace std;

int main()
{

   // prompt the user
   cout << "Please enter a positive dollar amount (ex: 4.23): ";
   double dollars;
   cin >> dollars;

   //convert to cents
   int  cents = (int)(dollars * 100);

   //DOLLARS
   int dollar = cents/100;
   cout << "Dollars: " << dollar << endl;
   cents %= 100;

   //100 dollar bills
   cout << "\t100s: " << dollar/100 << endl;
   dollar %= 100;

   //20 dollar bills
   cout << "\t20s: " << dollar/20 << endl;
   dollar %= 20;

   //10 dollar bills
      cout << "\t10s: " << dollar/10 << endl;
      dollar %= 10;

      //5 dollar bills
      cout << "\t5s: " << dollar/5 << endl;
      dollar %= 5;

      //1 dollar bills
      cout << "\t1s: " << dollar << endl;

      //cents
      cout << "Cents: " << cents << endl;
      //QUARTERS
      cout << "\tQuarters: " << cents/25 << endl;
      cents %= 25;

      //DIME
      cout << "\tDimes: " << cents / 10 << endl;
      cents %= 10;

      //NICKLES
         cout << "\tNicles: " << cents / 5 << endl;
         cents %= 5;

         //PENNIES
         cout << "\tPennies: " << cents << endl;

return 0;

}
