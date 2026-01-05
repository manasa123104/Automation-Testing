# How to Use Test Management Tool - Step-by-Step Guide

## 🎯 Overview
This guide shows you how to use the test management tool interface to create, manage, and track your test cases.

---

## 📋 Understanding the Interface

### Main Components:

1. **Top Header Bar**
   - Title: "Test Scenarios & Test Cases"
   - Navigation: Data, Automations, Interfaces, Forms

2. **Left Sidebar**
   - Test Cases section
   - Grid view (currently active)
   - "+ Create new..." button
   - Search bar

3. **Main Grid View**
   - Table with columns: Test ID, Feature Area, Scenario, Steps, Expected Result, Status

---

## 🚀 Step-by-Step Instructions

### **Step 1: Access the Test Management Tool**

1. Open your test management tool/application
2. Navigate to the "Data" section (top right navigation)
3. You should see "Test Scenarios & Test Cases" in the grid view

---

### **Step 2: View Existing Test Cases**

1. The grid view shows all test cases in a table format
2. You can see:
   - **Test ID**: Unique identifier (e.g., AO-11, PR-32)
   - **Feature Area**: Category tags (Auth & Onboarding, Properties, etc.)
   - **Scenario**: Description of what's being tested
   - **Steps**: Test procedure steps
   - **Expected Result**: What should happen
   - **Status**: Current test status (Untested, Tested & Works)

3. Use the search bar ("Q Find a view") to filter test cases

---

### **Step 3: Create a New Test Case**

#### Option A: Using the "+ Create new..." Button

1. **Click the "+ Create new..." button** in the left sidebar
2. A form or dialog will appear
3. Fill in the following fields:

   **Test ID:**
   - Format: [Feature Prefix]-[Number]
   - Example: AO-11 (Auth & Onboarding - Test 11)
   - Follow the existing pattern

   **Feature Area:**
   - Select from dropdown or type
   - Options: "Auth & Onboarding", "Properties", "Navigation & Routing", etc.
   - This creates a colored tag

   **Scenario:**
   - Enter a clear description
   - Example: "Apple Sign-In Authentication (iOS/Android)"

   **Steps:**
   - Enter step-by-step instructions
   - Format: "1. Launch the application"
   - "2. Navigate to login screen"
   - "3. Select 'Continue with Apple' option"
   - etc.

   **Expected Result:**
   - List what should happen
   - Format: "1. Apple Sign-In dialog appears..."
   - "2. Authentication succeeds..."
   - etc.

   **Status:**
   - Select: "Untested" (default for new cases)
   - Or: "Tested & Works" if already tested

4. **Click "Save" or "Create"**

---

#### Option B: Add Row Directly in Grid

1. **Scroll to the bottom** of the grid table
2. **Click on an empty row** or look for an "Add Row" button
3. **Fill in the columns** directly:
   - Click in each column cell
   - Enter the information
4. **Press Enter or Tab** to move to next cell
5. The row will auto-save

---

### **Step 4: Update Existing Test Case (Apple Sign-In - AO-11)**

Since you already have the Apple Sign-In test case (AO-11) showing as "Untested", here's how to update it:

1. **Find the test case** in the grid:
   - Look for Test ID: AO-11
   - Or search for "Apple Sign-In"

2. **Click on the row** to select it

3. **Update the Status:**
   - Click on the "Status" column for AO-11
   - Change from "Untested" to "Tested & Works"
   - Or select appropriate status

4. **Edit other fields if needed:**
   - Click on any column cell to edit
   - Update Steps if you found issues
   - Update Expected Result if needed

5. **Changes auto-save** or click "Save"

---

### **Step 5: Filter and Search Test Cases**

1. **Use the search bar:**
   - Click "Q Find a view" search box
   - Type keywords (e.g., "Apple", "Auth", "AO-11")
   - Results filter automatically

2. **Filter by Feature Area:**
   - Click on a Feature Area tag
   - View only that category

3. **Filter by Status:**
   - Click on Status column header
   - Select filter option

4. **Sort columns:**
   - Click the ↑↓ arrows in column headers
   - Sort by Test ID, Feature Area, Status, etc.

---

### **Step 6: View Test Case Details**

1. **Click on a test case row** to view full details
2. Or **double-click** to open in detail view
3. You'll see:
   - Complete steps
   - Full expected results
   - Test execution history
   - Attachments (videos, screenshots)

---

## 📝 Specific Steps for Your Apple Sign-In Test Case

### **Current Status:**
- Test ID: AO-11
- Feature Area: Auth & Onboarding
- Status: Untested
- Scenario: Apple Sign-In Authentication (iOS/Android)

### **To Update After Testing:**

1. **After you complete testing:**
   - Open the test management tool
   - Find test case AO-11
   - Click on the Status column

2. **Change Status:**
   - Select "Tested & Works" if everything passed
   - Or create a new status if issues found

3. **Add Notes/Comments:**
   - Look for a "Notes" or "Comments" field
   - Add your findings from testing
   - Reference your video recording

4. **Attach Files:**
   - Look for attachment/upload option
   - Attach your video from Spanr app
   - Attach screenshots if any

---

## 🎨 Understanding the Tags/Colors

### **Feature Area Tags:**
- **Pink**: Auth & Onboarding
- **Yellow**: Properties
- **Orange**: Navigation & Routing
- **Light Blue**: Care Plans & Activities

### **Status Tags:**
- **Light Blue**: Untested
- **Darker Blue**: Tested & Works

---

## 🔧 Advanced Features

### **Bulk Actions:**
1. **Select multiple rows** (checkboxes or Shift+Click)
2. **Bulk update status** for multiple test cases
3. **Export** selected test cases

### **Views:**
- **Grid View**: Current table view (what you see)
- **List View**: Alternative view (if available)
- **Kanban View**: Card-based view (if available)

### **Automations:**
- Click "Automations" in top navigation
- Set up automated test execution
- Schedule test runs

---

## ✅ Quick Checklist

- [ ] Found the test management tool
- [ ] Located test case AO-11 (Apple Sign-In)
- [ ] Understand how to update status
- [ ] Know how to create new test cases
- [ ] Can filter and search test cases
- [ ] Ready to update after testing

---

## 🆘 Troubleshooting

### **Can't find the test case?**
- Use the search bar: Type "AO-11" or "Apple"
- Check if you're in the right view (Grid view)
- Verify you're in the "Data" section

### **Can't edit a field?**
- Make sure you have edit permissions
- Try clicking directly on the cell
- Check if the row is locked/archived

### **Changes not saving?**
- Look for a "Save" button
- Check your internet connection
- Try refreshing the page

### **Need to add more steps?**
- Look for a "Details" or "Expand" option
- Click on the test case to open full view
- Add steps in the detailed view

---

## 📞 Next Steps

1. **Test the Apple Sign-In feature** (follow your testing guide)
2. **Record with Spanr app** (as planned)
3. **Update test case AO-11** in this tool:
   - Change status to "Tested & Works"
   - Add notes about your findings
   - Attach video recording
4. **Share with your uncle** (include link to this test case)

---

## 💡 Pro Tips

1. **Use consistent Test IDs**: Follow the pattern (AO-XX, PR-XX, etc.)
2. **Keep Status updated**: Update as you test
3. **Add detailed notes**: Helpful for future reference
4. **Attach evidence**: Videos and screenshots prove testing
5. **Use search**: Faster than scrolling through many test cases

---

**You're all set! Start by finding test case AO-11 and updating it after you complete your testing.** 🚀

