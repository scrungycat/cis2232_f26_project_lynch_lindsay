![trident roastery logo](https://raw.githubusercontent.com/scrungycat/cis2232_f26_project_lynch_lindsay/refs/heads/main/Project/coffee_order_app/src/main/resources/static/images/tridentRLogo_256.png)[^1]

# Trident Roastery | Coffee Order Tracker 
Project repo for CIS 2232 - AOOP

## Development Team 
* **Business Client**: *Misha Nosachenko*
* **Lead Developer**: *Lindsay Lynch*
* **PM/QA**: *Isaac Morrison*
***
## Description 

This repo hosts an assortment of assignments and a main final project as outlined for CIS2232 - AOOP. This web application is being created following the business topic outline provided by my BA, Misha.

The goal of the project is to create a central ordering & tracking portal for staff use at Trident Roastery. <br/>
The end product will allow staff to input order details & view a persistent history of orders submitted. 
***
## Color 

* Main Color: Coffee Brown | ![#6F4E37](https://dummyimage.com/20x20/6f4e37/6f4e37) #6F4E37[^2]
* Secondary Color: TBD
***
## Required Fields 
| Name	        | Data Type |	Description            |
|:--------------: |:---------:|:------------| 
| `id`	        | `int`	    | Unique identifier per order & PK in DB |
| `customerName`| `String`  | Customer’s name |
| `drinkType`	| `String`  | Brewed coffee, latte, cappuccino, americano | 
| `drinkSize`	| `String`  | Small, medium, large | 
| `milkType`	| `String`  | None, whole, oat, almond | 
| `quantity`	| `int`     | Number of identical drinks, at least one | 
| `unitPrice`	| `double`  | Base price per drink | 
| `extraShots`	| `int`     | Extra shots per drink | 
| `milkSurchage`| `double`  | Milk charge per drink | 
| `orderStatus`	| `String`  | Pending, preparing, completed, cancelled |
***
## Calculation 

Order calculation will be processed when staff enter a new order entry for a customer.

`Subtotal = (unitPrice + extraShots x $1.00 + milkSurcharge) x quantity`

Milk surcharge: 0 for none or whole milk, $0.75 for oat or almond milk.

### *Additional calculation notes*

1. Validates if at least 1 coffee entered into the order form before submission.
   
2. Validates if values are appropriate to the fields throughout data entry. 

3. Adds cost of selected coffee size to any requested extra espresso shots and/or premium milk products.
   
4. Multiplies order item as necessary if multiple identical drinks are requested by the same customer.

After confirming no other items are to be included in the order the subtotal is calculated & taxed, and finally both values are added together to provide the customer's grand total for their order. <br/>

The order information will be written in a local .json file, and later a separate SQL database.
***
## Report Details 

To be determined in a future sprint.[^3]
***
#### Credits & Thanks
[^1]: Open-licensed logo created using SVGRepo.com - [Flaming Trident Vector](https://www.svgrepo.com/svg/499171/flaming-trident)
[^2]: HEX color box created using Dummy Image, created by Russell Heimlich (@kingkool68) and distributed freely under a MIT License - [dummyimage.com](https://dummyimage.com/) | [kingkool68 dummyimage repo](https://github.com/kingkool68/dummyimage)
[^3]: Special thanks to Adam Pritchard's [Markdown Cheatsheet](https://github.com/adam-p/markdown-here/wiki/markdown-cheatsheet#code) guide for formatting help
