var firebaseConfig = {
  apiKey: "AIzaSyABC45epEv116XDWIa-Oag1g9jzjyLDBrY",
  authDomain: "mpt-web-tester.firebaseapp.com",
  projectId: "mpt-web-tester",
  storageBucket: "mpt-web-tester.appspot.com",
  messagingSenderId: "818343226778",
  appId: "1:818343226778:web:67be5a6916ab3b02c71e23"
  
};
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  // Initialize variables
  const auth = firebase.auth();
  const database = firebase.firestore();
  const phone = document.getElementById('phone');
  const password = document.getElementById('password');
  const btnLog = document.getElementById('btnLogin');
  var doctorData= {};
  
  
  btnLog.addEventListener('click', e => {
    e.preventDefault();
    validate_input();
  
    
  });
  
  // Validate Functions

  function validate_input(){

    if (validate_phone() && validate_password()) {
        validate_login();
      }
      else{
        alert('error')
          return

      };
  

  }
  function validate_phone() {
    expression = /^[0-9]+$/
    if (expression.test(phone.value) == true) {
      // phone is good
      return true
    } else {
        
      // phone is not good
      alert('Phone number only numbers or field is empty')
      return false
    }
  }
  
  function validate_password() {
    // Firebase only accepts lengths greater than 6
    if (password.value < 6) {
        alert('Password is empty or too short!!')
      return false
    } else {
        
      return true
      
    }
  }
  
  function validate_field(field) {
    if (field == null) {
      return false
    }
  
    if (field.length <= 0) {
      return false
    } else {
      return true
    }
  }
  
  function validateOnlinePassword(doctorData){
     
    if(doctorData['Password']== password.value){
      window.document.location = "../html/docHomePage.html" +'?docName='+doctorData['LastName'];
    }else{
      alert("Wrong password")
    }
  };

  function validate_login(){
    const phoneCheck = database.collection('Doctor').doc(phone.value);
        phoneCheck.get()
        .then((docSnapshot) => {
          if (docSnapshot.exists) {
            phoneCheck.onSnapshot((doc) => {       
              doctorData=docSnapshot.data();
              validateOnlinePassword(doctorData);
            });
          } else {
            alert("not found")// create the document
          }
        });

  }
  


