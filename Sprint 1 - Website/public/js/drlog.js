var firebaseConfig = {
  apiKey: "AIzaSyABC45epEv116XDWIa-Oag1g9jzjyLDBrY",
  authDomain: "mpt-web-tester.firebaseapp.com",
  projectId: "mpt-web-tester",
  storageBucket: "mpt-web-tester.appspot.com",
  messagingSenderId: "818343226778",
  appId: "1:818343226778:web:713056b90e62dd83c71e23"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
// Initialize variables
const auth = firebase.auth();
const database = firebase.firestore();
const phone = document.getElementById('phone');
const password = document.getElementById('password');


const btnLog = document.getElementById('btnLogin');
const updateBtn = document.getElementById('updateBtn');
const readBtn = document.getElementById('readBtn');
const removeBtn = document.getElementById('removeBtn');



const emailTrue = Boolean(false);
const passTrue = Boolean(false);
var doctorData= {};


btnLog.addEventListener('click', e => {
  e.preventDefault();
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
});

// Validate Functions
function validate_email(email) {
  expression = /^[^@]+@\w+(\.\w+)+\w$/
  if (expression.test(email) == true) {
    // Email is good
    return true
  } else {
    // Email is not good
    return false
  }
}

function validate_password(password) {
  // Firebase only accepts lengths greater than 6
  if (password < 6) {
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
    window.location.href = "../html/DrHome.html"
  }else{
    alert("Wrong password")
  }
};

function validateLoginDetail(){
  if ( validatePhoneNumber() && validatePassword() )
  {
      validateOnline();
  }

  return false;
}



