
- **Added the shop name**:  
  `My Tech Shop`

- **Included Product Names and their Corresponding Parts Names**:
    - **Product Names**:
        - Gaming Desktop
        - Office Laptop
        - Student Laptop
        - All-in-One Desktop
        - Ultrabook
    - **Parts Names**:
        - CPU
        - GPU
        - RAM
        - Storage Device
        - PSU

- about.html:
  - Added title “About - My Tech Shop” on line 14
  - Added a description of the company and navigation back to the main screen on lines 18–39
- mainscreen.html:
  - Added a button to navigate to the “About” page on line 20
- about.html:
    - Added a button to navigate to the “About” page on line 38

- BootStrapData.java:
  - Added an if statement on line 49 to check whether the parts count and products count are zero before adding the sample inventory
  - Added parts on line 52 - 86
  - Added products on line 89 - 99

- mainscreen.html:
  - Added "Buy Now" button on Line 86 - 89
  - The button initiates the purchase process by making an HTTP POST request to the /buyProduct endpoint
- BuyNowController:
  - Created new controller to handle the "Buy Now" logic
  - Mapped for purchase success (/purchaseSuccess) / error pages on Line 44 - 54 (/purchaseError)
- purchaseSuccess.html:
  - Created a new HTML template to display a confirmation message for successful purchases
- purchaseError.html:
  - Created a new HTML template to display an error message for unsuccessful purchases

- Part.java
  - Added minInv and maxInv fields to the Part class on Line 32 - 35
  - Updated constructors including minInv and maxInv on Line 49 - 50
  - Wrote getter and setter methods for minInv and maxInv on Line 63 - 77
  - Created validation method to check if inventory is valid on Line 79 - 82

- BootStrapData.java
  - Modified the sample inventory to include minInv and maxInv for all parts

- InHousePartForm.html
  - Added fields for min and max Inventory on Line 24 - 29

- OutsourcedPartForm.html
  - Added fields for min and max Inventory on Line 25 - 30

- application.properties
  - Renamed it to a new name on Line 6

- AddInhousePartController
  - Added validation logic on Line 46 - 68
  
- AddOutsourcedPartController
  - Added validation logic on Line 45 - 59

- EnufPartsValidator
    - Added validation to ensure part inventory remains within the min and max range

- PartTest
    - Added Unit Tests for maximum and minimum inventory fields on Line 38 - 76

- DeletePartValidator.java
    - Class DeletePartValidator was never used, so it has been deleted

Additional updates

- mainscreen.html
    - added missing max inventory value column and min inventory value column
- productForm.html
    - added missing 'back to main screen' 