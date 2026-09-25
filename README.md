# Trident Roastery | Coffee Order Tracker #
Project repo for CIS 2232 - AOOP

## Development Team ##
* Business Client: Misha Nosachenko
* Lead Developer: Lindsay Lynch
* PM/QA: Isaac Morrison

## Description ##

This repo hosts an assortment of assignments as outlined for CIS2232 - AOOP.

The goal of the web application is to provide a central order submission & tracking software for Trident Roastery. <br />
The end product will allow staff to input order details & view a persistent history of orders submitted. 

## Color ##

* Main Color: Coffee Brown | ![#6F4E37](https://dummyimage.com/20x20/6f4e37/6f4e37) #6F4E37
* Secondary Color: TBD

## Required Fields ##
| Name	        | Data Type |	Description            |
|:--------------: |:---------:|:------------| 
| `id`	        | `int`	    | Unique identifier for database table |
| `customerName`| `String`  | Customer’s name |
| `drinkType`	| `String`  | Brewed coffee, latte, cappuccino, americano | 
| `drinkSize`	| `String`  | Small, medium, large | 
| `milkType`	| `String`  | None, whole, oat, almond | 
| `quantity`	| `int`     | Number of identical drinks, at least one | 
| `unitPrice`	| `double`  | Base price per drink | 
| `extraShots`	| `int`     | Extra shots per drink | 
| `milkSurchage`| `double`  | Milk charge per drink | 
| `orderStatus`	| `String`  | Pending, preparing, completed, cancelled |

## Calculation ##

Order calculation will be processed when staff enter a new order entry for a customer.

1. Validates if at least 1 coffee entered into the order form before submission.
   
2. Validates if values are appropriate to the fields throughout submission. 

3. Adds cost of selected coffee size to any requested extra espresso shots and/or premium milk products.
   
4. Multiplies order item as necessary if multiple of the same drink are requested.

After confirming no other items are to be included in the order the subtotal is calculated & taxed, and finally both values are added together to provide the customer's grand total for their order. 

## Report Details ##

To be determined in a future sprint.
