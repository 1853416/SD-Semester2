var firebaseConfig = {
  apiKey: "AIzaSyABC45epEv116XDWIa-Oag1g9jzjyLDBrY",
    authDomain: "mpt-web-tester.firebaseapp.com",
    databaseURL: "https://mpt-web-tester-default-rtdb.firebaseio.com",
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
const email = document.getElementById('email');
const password = document.getElementById('password');
const first_name = document.getElementById('first_name');
const last_name = document.getElementById('last_name');
const idNum = document.getElementById('idNum');
const field = document.getElementById('field');
const qualifications = document.getElementById('qualifications');
const years = document.getElementById('years');
const btnRegister = document.getElementById('btnRegister');

const updateBtn = document.getElementById('updateBtn');
const readBtn = document.getElementById('readBtn');
const removeBtn = document.getElementById('removeBtn');


const usersCollection = database.collection('Doctor');

btnRegister.addEventListener('click', e => {
  // e.preventDefault();
  
  const ID = usersCollection.doc(phone.value).set({
    Email: email.value,
    Fields: field.value,
    "First Name": first_name.value,
    ID: idNum.value,
    "Last Name":last_name.value,
    Password: password.value,
    Qualifications: qualifications.value,
    "Years of Experience":years.value


  })
  .then(()=>{
    alert('User Created!!')
  window.location.replace("../html/LoginDR.html");
    console.log('Data has been saved successfully !')})
  .catch(error => {
    console.error(error)
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