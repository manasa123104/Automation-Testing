# Airtable Test Case Guide - AO-11 Apple Sign-In

## 📋 Current Test Case Details

### **Test ID:** AO-11
### **Feature Area:** Auth & Onboarding (Pink tag)
### **Scenario:** Apple Sign-In Authentication (iOS/Android)
### **Status:** Untested (Light blue tag)

---

## ✅ Review of Current Test Case

### **Steps (5 steps):**
1. ✅ Launch the application
2. ✅ Navigate to the login screen
3. ✅ Select "Continue with Apple" option
4. ✅ Complete Apple authentication flow
5. ✅ Choose to share or hide email address

### **Expected Results (6 results):**
1. ✅ Apple Sign-In dialog appears with proper scopes (FULL_NAME, EMAIL)
2. ✅ AppleAuthentication.signInAsync() returns valid credential with identityToken
3. ✅ OAuthProvider credential created for 'apple.com'
4. ✅ User successfully authenticated and redirected to authenticated section
5. ✅ Auth state updated correctly
6. ✅ Apple provider data stored in authProviders

**Status:** All steps and expected results are properly defined! ✅

---

## 🎯 What to Do Next

### **Step 1: Test the Feature**
Follow your testing guide (`WHAT_TO_DO_NOW.md` or `Testing_Guide_Apple_SignIn.md`):
1. Open Spanr app and start recording
2. Test the Apple Sign-In flow
3. Follow all 5 steps listed
4. Verify all 6 expected results
5. Stop recording and save video

### **Step 2: Update Status in Airtable**

#### **Option A: Quick Update**
1. **Click on the "Status" field** (currently shows "Untested")
2. **Select "Tested & Works"** from the dropdown
3. Changes save automatically

#### **Option B: Detailed Update**
1. **Click on the Status field**
2. **Choose appropriate status:**
   - ✅ **"Tested & Works"** - If all tests passed
   - ⚠️ **"Needs Review"** - If issues found (if available)
   - ❌ **"Failed"** - If critical issues (if available)

### **Step 3: Add Comments/Notes**

#### **Using the Comments Sidebar:**
1. **Click in the comments area** (right side: "Start a conversation")
2. **Type your test results**, for example:
   ```
   Tested on: [Date]
   Device: [Your device]
   
   Results:
   ✅ All steps completed successfully
   ✅ All expected results verified
   ✅ Performance: ~4-5 seconds total flow
   
   Video attached: [if you can attach]
   ```

3. **Mention collaborators** if needed:
   - Type `@` to mention your uncle or team members
   - They'll get notified

#### **Or Add a Notes Field (if available):**
- Look for a "Notes" or "Test Results" field
- Add your findings there

### **Step 4: Attach Video/Evidence**

1. **In the comments section:**
   - Click attachment icon (paperclip) if available
   - Upload your video from Spanr app

2. **Or use Airtable attachments:**
   - Look for an "Attachments" field
   - Drag and drop your video file
   - Or click to browse and upload

---

## 📝 Sample Comment/Update to Add

After testing, add this in the comments:

```
Test Execution - AO-11 Apple Sign-In

Date: [Your date]
Tester: Manasa
Device: [Your device model]

Test Results:
✅ Step 1: App launched successfully (~2 seconds)
✅ Step 2: Login screen loaded (~1 second)
✅ Step 3: Apple Sign-In button visible and clickable
✅ Step 4: Authentication completed successfully (~3 seconds)
✅ Step 5: Email option selection worked

Expected Results Verification:
✅ Apple Sign-In dialog appeared with FULL_NAME and EMAIL scopes
✅ Authentication returned valid credential with identityToken
✅ OAuthProvider credential created for apple.com
✅ User redirected to authenticated section
✅ Auth state updated correctly
✅ Apple provider data stored

Total Flow Time: ~6-7 seconds
Status: All tests PASSED

Video: [Attach your recording]
```

---

## 🔄 Workflow Summary

```
1. Test Feature
   ↓
2. Record with Spanr
   ↓
3. Update Status: Untested → Tested & Works
   ↓
4. Add Comments with Results
   ↓
5. Attach Video
   ↓
6. @Mention Uncle (optional)
   ↓
7. Done! ✅
```

---

## 💡 Pro Tips for Airtable

### **Keyboard Shortcuts:**
- **Click field** to edit
- **Tab** to move to next field
- **Enter** to save changes
- **Esc** to cancel

### **Status Options:**
- Check what status options are available in your Airtable
- Common options: Untested, In Progress, Tested & Works, Failed, Blocked

### **Collaboration:**
- Use `@mention` to notify team members
- Comments are great for tracking test history
- Attachments help provide evidence

### **Filtering:**
- You can filter by Status to see:
  - All "Untested" cases
  - All "Tested & Works" cases
  - By Feature Area (Auth & Onboarding)

---

## ✅ Checklist Before Marking Complete

- [ ] All 5 steps tested
- [ ] All 6 expected results verified
- [ ] Video recorded with Spanr
- [ ] Status updated in Airtable
- [ ] Comments added with results
- [ ] Video attached (if possible)
- [ ] Team notified (if needed)

---

## 🎯 Current Status

**Your test case AO-11 is ready to test!**

- ✅ Steps are clear and complete
- ✅ Expected results are well-defined
- ✅ Status is set to "Untested" (ready for testing)
- ⏳ Waiting for you to complete testing and update

**Next Action:** Start testing using your testing guides, then come back to update the status and add comments!

---

**You're all set! The test case looks perfect. Just test it and update the status when done.** 🚀



