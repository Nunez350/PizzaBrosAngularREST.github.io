   
    // Submit form with name function.
    function submit_by_name() {
        var name = document.getElementById("name").value;
        var email = document.getElementById("email").value;
        var Username = document.getElementById("Username").value;
        if (validation()){ // Calling validation function
        //var x = document.getElementsByName('form_name');
        //x[0].submit(); //form submission
        alert(" Username : " + Username + " \n Email : " + email + " \n Congratulations: "  + "\n\n Welcome to the Pizza ZONE!......");
        }
    }  
    
    // Name and Email validation Function.
    function validation() {
        var name = document.getElementById("name").value;
        var email = document.getElementById("email").value;
        var Username = document.getElementById("Username").value;
        var emailReg = /.+@.+\..+$/;
        var password1 = document.getElementById("password1").value;
        var password2 = document.getElementById("password2").value;
        if (name === '' || email === ''|| Username ==='' ) {
        alert("Please fill all fields...!!!!!!");
        return false;
        } else if (!(email).match(emailReg)) {
            alert("Invalid Email...!!!!!!");
            return false;
        } else if (password1 != password2){
            alert("Passwords do not match!"); 
            return false;
        }
        else {
        return true;
        }
    }