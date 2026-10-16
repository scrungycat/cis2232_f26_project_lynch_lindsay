# Trident Roastery | Coffee Order Tracker 
*CIS-2232: Project Repo*

![trident roastery logo](https://raw.githubusercontent.com/scrungycat/cis2232_f26_project_lynch_lindsay/refs/heads/main/Project/coffee_order_app/src/main/resources/static/images/tridentRLogo_256.png)[^1]

## 🧩 Development Team 
* **Business Client**: *Misha*
* **Lead Developer**: *Lindsay*
* **PM/QA**: *Isaac*
***
## 📜 Description 

This repo hosts an assortment of assignments and a main final project as outlined for CIS-2232. Work Item/Milestone tracking [found here](https://github.com/users/scrungycat/projects/1)

This application is being created by following the provided business topic outline by the team's BA, Misha. Creation of the project is accomplished using the following languages, frameworks, and other tools/utilities:
- Java
  - Spring & Thymeleaf (MVC)
- HTML/CSS/JS
  - chart.js (Graph/Reports)
- MySQL
  - MariaDB
  - XAMPP/Apache
- GraphQL
- et al.

The goal of the project is to create a central ordering & tracking portal for staff use at Trident Roastery. <br/>
The end product will allow staff to input order details & view a persistent history of orders submitted. 
***
## 🎨 Color 

* Main Color: Coffee Brown | ![#6F4E37](https://dummyimage.com/20x20/6f4e37/6f4e37) #6F4E37[^2]
* Secondary Color: TBD
***
## 📓 Required Fields 
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
## 🧮 Calculation 

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
## 📊 Report Details 

### Receipt Reporting
**Use Case**: For retrieving customer order receipts to print out on demand/as needed for orders submitted to the system. 

**Output**: Raw data, categorized neatly in table format.

**Description**:
This tool will allow pulling customer receipts to review order items & related information by entering their unique order number (key).

**Report Data Includes**:
- Individual order items with related data
  - Drink Type
  - Size
  - Modifications (milk type, addt'l espresso shots)
  - Quantity
- Subtotal
- Taxes
- Grand Total

Should be organized in a fashion that allows an easily accessible/readable receipt for that order. 

***

### Sales Report Overview
**Use Case**: End-of-week reporting for the business to allow for sales review/forecasting, easy printing, and review for accounting/bookkeeping. 

**Output**: Sales data organized visually in a graph format (bar or line graph for two tandem X-axis data groups).

**Description**:
This tool provides an overview of sales data for a set range of dates as requested by the user by compiling and outputting information in graph format. 

**Report Data Includes**:
- Dates in range (Y axis)
- Number of individual sales (X axis)
- Dollar amounts of sale per day (X axis)
- Grand total of money in (separate output outside of the graph)

This will allow comparison and evaluation of sales performance on a particular range of dates over a period of time requested by the user. 

[^3]
***

#### ✒️ Credits & Thanks
[^1]: Open-licensed logo created using SVGRepo.com - [Flaming Trident Vector](https://www.svgrepo.com/svg/499171/flaming-trident)
[^2]: HEX color box created using Dummy Image, created by Russell Heimlich (@kingkool68) and distributed freely under a MIT License - [dummyimage.com](https://dummyimage.com/) | [kingkool68 dummyimage repo](https://github.com/kingkool68/dummyimage)
[^3]: Special thanks to Adam Pritchard's [Markdown Cheatsheet](https://github.com/adam-p/markdown-here/wiki/markdown-cheatsheet#code) guide for formatting help
