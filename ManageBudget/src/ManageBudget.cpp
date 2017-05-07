/*
ManageBudget.cpp
May 7, 2017
Cesar Caceres
*/

#include <iostream>
#include <iomanip>
using namespace std;

float getIncome();
float getBudgetLiving();
float getActualLiving();
float getActualTax();
float getActualTithing();
float getActualOther();
double computeTithing(float income);
float computeTax(float monthlyIncome);
double budgetOther(double income, double budgetTithing, double budgetLiving,
		double taxWithheld);
float actualDifference(float income, float actualTax, float actualTithing,
		float actualLiving, float actualOther);
float budgetDifference();
void display(float income,float  budgetLiving, float actualTax,
		float actualTithing,float actualLiving,float actualOther);

/**********************************************************************
 * MAIN
 * Asks for input about the user's budget and displays monthly expenses
 * on a table on the screen
 ***********************************************************************/
int main()
{

	//variables
	   float monthIncome;
	   float budgetLivingExp;
	   float actualLivingExp;
	   float taxWithheld;
	   float titheOffer;
	   float otherExp;


	   //set display for money
	   cout.setf(ios::fixed);
	   cout.setf(ios::showpoint);
	   cout.precision(2);

	   cout << "This program keeps track of your monthly budget\n"
	          << "Please enter the following:\n";
	     monthIncome = getIncome();
	     budgetLivingExp = getBudgetLiving();
	     actualLivingExp = getActualLiving();
	     taxWithheld = getActualTax();
	     titheOffer = getActualTithing();
	     otherExp = getActualOther();

	     display(monthIncome,  budgetLivingExp, taxWithheld, titheOffer,
	    		 actualLivingExp, otherExp);

return 0;
}

/**********************************************************************
 * getIncome
 * Asks for input about the user's income and returns it to main()
 ***********************************************************************/

float getIncome()
{
	float monthIncome;
	cout << "\tYour monthly income: ";
	cin >> monthIncome;
	return monthIncome;
}

/**********************************************************************
 * getBudgetLiving
 * Asks for input about the user's budgeted living expenses and returns
 * it to main()
 ***********************************************************************/

float getBudgetLiving()
{
	float budgetLivingExp;
	 cout << "\tYour budgeted living expenses: ";
	 cin >> budgetLivingExp;
	 return budgetLivingExp;
}

/**********************************************************************
 * getActualLiving
 * Asks for input about the user's actual living expenses and returns it
 * to main()
 ***********************************************************************/

float getActualLiving()
{
	float actualLivingExp;
	cout << "\tYour actual living expenses: ";
    cin >> actualLivingExp;
    return actualLivingExp;
}

/**********************************************************************
 * getActualTax
 * Asks for input about the user's actual tax expenses and returns it
 * to main()
 ***********************************************************************/
float getActualTax()
{
	float taxWithheld;
	cout << "\tYour actual taxes withheld: ";
	cin >> taxWithheld;
	return taxWithheld;
}

/**********************************************************************
 * getActualTithing
 * Asks for input about the user's actual tithe offerings and returns it
 * to main()
 ***********************************************************************/
float getActualTithing()
{
	float titheOffer;
    cout << "\tYour actual tithe offerings: ";
    cin >> titheOffer;
    return titheOffer;
}

/**********************************************************************
 * getActualOther
 * Asks for input about the user's actual other expenses and returns the
 * value to main()
 ***********************************************************************/
float getActualOther()
{
	float otherExp;
    cout << "\tYour actual other expenses: ";
    cin >> otherExp;
	return otherExp;
}

/**********************************************************************
 * display
 * This function takes all the inputs gathered from the other functions
 * and puts in on the screen in a legible format.
 ***********************************************************************/
void display(float income,float  budgetLiving, float actualTax,
		     float actualTithing,float actualLiving,float actualOther)
{
	//variables
	double budgetTithing = computeTithing(income);
	double taxWithheld = computeTax(income);
	float budgetDiff = budgetDifference();
	double budOther = budgetOther(income, budgetTithing, budgetLiving,
		  taxWithheld);
	float actualDiff = actualDifference(income, actualTax, actualTithing,
	      actualLiving, actualOther);

	 cout << "\n";    //blank space
	 //Reporting monthly expenses from saved variables
	 cout << "The following is a report on your monthly expenses\n";

	 // header of table
	 cout << "\tItem                  Budget          Actual\n"
		  << "\t=============== =============== ===============\n";

	 //display first row
	 cout << "\tIncome          $" << setw(11) << income
		  << "    $" << setw(11) << income << endl;

	 //display second row
	 cout << "\tTaxes           $" << setw(11) << taxWithheld
		  << "    $" << setw(11) << actualTax << endl;

	 //display third row
	 cout << "\tTithing         $" << setw(11) << budgetTithing
		  << "    $" << setw(11) << actualTithing << endl;

	 //display fourth row
	 cout << "\tLiving          $" << setw(11) << budgetLiving
		  << "    $" << setw(11) << actualLiving << endl;

	 //display fifth row
	 cout << "\tOther           $" << setw(11) << budOther
		  << "    $" << setw(11) << actualOther << endl;

	 //display Difference row
	 cout << "\t=============== =============== ===============\n"
		  << "\tDifference      $" << setw(11) << budgetDiff
		  << "    $" << setw(11) << actualDiff << endl;
return;
}

/**********************************************************************
 * computeTithing
 * This function determines the amount required to be a full tithe payer
 * and returns it to display()
 ***********************************************************************/
double computeTithing(float income)
{
	return income * 0.10;
}

/**********************************************************************
 * budgetOther
 * This function takes inputs in order to calculate the other budgeted
 * expenses and returns it to display().
 ***********************************************************************/
double budgetOther(double income, double budgetTithing, double budgetLiving,
		double taxWithheld)
{
return (income - budgetTithing - budgetLiving - taxWithheld);
}

/**********************************************************************
 * budgetDifference
 * This function sets the value of the budgeted difference to 0
 * which is returned to display().
 ***********************************************************************/
float budgetDifference()
{
	return 0;
}

/**********************************************************************
 * actualDifference
 * This function takes inputs to calculate the value of the actual
 * difference from income and all other expenses and returns it to
 * display().
 ***********************************************************************/
float actualDifference(float income, float actualTax, float actualTithing,
		float actualLiving, float actualOther)
{
	return income - actualTax - actualTithing - actualLiving - actualOther;
}


float computeTax(float monthlyIncome)
{
   //declare variables
   float yearIncome = monthlyIncome * 12;
   float yearlyTax;
   float monthlyTax;

   if (yearIncome < 15100)
      yearlyTax = yearIncome * 0.10;
else if (yearIncome < 61300)
   yearlyTax = 1510 + 0.15 * (yearIncome - 15100);
else if (yearIncome < 123700)
   yearlyTax = 8440 + 0.25 * (yearIncome - 61300);
else if (yearIncome < 188450)
   yearlyTax = 24040 + 0.28 * (yearIncome - 123700);
else if (yearIncome < 336550)
   yearlyTax = 42170 + 0.33 * (yearIncome - 188450);
else
   yearlyTax = 91043 + 0.35 * (yearIncome - 336550);

monthlyTax = yearlyTax / 12;

return monthlyTax;
}

