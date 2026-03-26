# Laboratory 2

## Xpath/CSS practical task
### Demo website: https://opensource-demo.orangehrmlive.com/

Open elements tab in the developer tools (CTRL + SHIFT + I) or “Inspect” in the context menu.
Using Xpath perform steps 1-7.
1.	Go to https://opensource-demo.orangehrmlive.com/web/index.php/auth/login and locate elements Username, Password and button Login.
2.	Login into demo application with the admin’s credentials.
3.	Locate element “Admin” in the side menu.
4.	Locate elements Username, User Role, Employee Name, Status and button Search.
5.	Locate elements in table “Records Found”: the 3rd row in the table, every cell in the column
6.	Status, the exact cell Employee Name in row 5, action delete and action edit in any row.
7.	Go to side menu, select Leave. Locate the element that invokes the calendar of the field From Date.
8.	Repeat steps 1 – 7 with CSS selectors.
9.	Save found locators and selectors in the text document and submit at ORTUS.

## XPath:
Username field : ```//input[@name="username"]```  
Password field: ```//input[@type="password"]```  
Login button: ```//button[@type="submit"]```  
Admin in side menu: ```//a[contains(@href,"admin")]```  
Username: ```//*[@class="oxd-userdropdown-name"]```  
Search: ```//input[@placeholder="Search"]```  
Username: ```//label[normalize-space()='Username']/parent::div/following-sibling::*//input```  
User Role: ```//label[normalize-space()='User Role']/parent::div/following-sibling::div```  
Employee Name: ```//label[normalize-space()='Employee Name']/parent::div/following-sibling::*//input```  
Status: ```//label[normalize-space()='Status']/parent::div/following-sibling::div```  
Search button: ```//button[normalize-space()="Search"]```  
3rd row: ```//div[@class="oxd-table-card"][3]```  
Column cell of 3rd row: ```(//div[@class="oxd-table-card"][3]//div[contains(@class,"oxd-table-cell")])[column-index]```  
Employee name in 5th row: ```(//div[@class="oxd-table-card"][5]//div[contains(@class,"oxd-table-cell")])[2]```  
Delete in any row: ```(//div[@class="oxd-table-card"][row-index]//div[contains(@class,"oxd-table-cell")])[6]//button[i[contains(@class, "bi-trash")]]```  
Edit in any row: ```(//div[@class="oxd-table-card"][ row-index]//div[contains(@class,"oxd-table-cell")])[6]//button[i[contains(@class, "bi-pencil-fill")]]```  
Leave on side menu: ```//a[contains(@href,"leave")]```  
From Date input: ```//label[normalize-space()='From Date']/parent::div/following-sibling::div```  

## CSS Selectors:
Username field : ```input[name=username]```  
Password field: ```input[name=password]```  
Login button: ```button[type=submit]```  
Admin in side menu: ```a[href*="admin"]```  
Username: ```oxd-userdropdown-name```  
Search: ```input[placeholder=Search]```  
Username: ```.oxd-input-group__label-wrapper + div input:nth-child(1)```  
User Role: ```.oxd-input-group .oxd-select-text-input:nth-child(1)```  
Employee Name: ```.oxd-input-group__label-wrapper + div input:nth-child(2)```  
Status: ```.oxd-input-group .oxd-select-text-input:nth-child(2)```  
Search button: ```button[type=submit]```  
3rd row: ```.oxd-table-body .oxd-table-card:nth-of-type(3)```  
Column cell of 3rd row: ```.oxd-table-body .oxd-table-card:nth-of-type(3) .oxd-table-cell:nth-of-type(column-index)```  
Employee name in 5th row: ```.oxd-table-body .oxd-table-card:nth-of-type(5) .oxd-table-cell:nth-of-type(2)```  
Delete in any row: ```.oxd-table-body .oxd-table-card:nth-of-type(5) .oxd-table-cell:nth-of-type(6) .bi-trash```  
Edit in any row: ```.oxd-table-body .oxd-table-card:nth-of-type(5) .oxd-table-cell:nth-of-type(6) .bi-pencil-fill```  
Leave on side menu: ```a[href*=leave]```  
From Date input: ```.oxd-form-row:first-of-type .oxd-grid-item:first-of-type input```
  
