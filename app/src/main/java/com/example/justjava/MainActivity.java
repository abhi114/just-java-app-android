/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     *
     */
    public void submitOrder(View view) {
        int price =calculatePrice();
        CheckBox CreamcheckBox =(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean isWhipped = CreamcheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_cream_checkbox);
        boolean isChocolate = chocolateCheckBox.isChecked();
        String userName = nameText();
        String message = createOrderSummary(price,isWhipped,isChocolate,userName);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for" + userName);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }








    }
    public  String nameText(){
        EditText editText = (EditText) findViewById(R.id.name_edit_text);
        String nameIs = "" + editText.getText();
        return nameIs;
    }

    public String createOrderSummary(int price,boolean check,boolean chocolate,String name){
        String summary = "Name: " + name +"\nAdd whipped cream?"+ check+"\nAdd Chocolate?"+chocolate+ "\nQuantity: " +  quantity + "\nTotal: " + price + "\nThank you!";
        return summary;
    }

    /**
     * Calculates the price of the order.
     *
     *  quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        CheckBox CreamcheckBox =(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean isWhipped = CreamcheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_cream_checkbox);
        boolean isChocolate = chocolateCheckBox.isChecked();
        int price=5;
        if(isWhipped==true&&isChocolate==true){
            price=price+3;
            return price*quantity;
        }else if(isWhipped==true){
            price=price+1;
            return  price*quantity;
        }else if(isChocolate==true){
            price=price+2;
            return  price*quantity;

        }else{
            return price*quantity;
        }

    }

    public void increment(View view) {
        if(quantity>=100){
            Toast.makeText(this,"the quantity cannot be greater than 100",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity=quantity+1;
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        if(quantity==1){
            Toast.makeText(this,"the quantity cannot be less than 1",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity=quantity-1;
        displayQuantity(quantity);
    }






    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */

    /**
     * This method displays the given text on the screen.
     */

}