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
const email = document.getElementById('email');
const password = document.getElementById('password');
const first_name = document.getElementById('first_name');
const last_name = document.getElementById('last_name');
const idNum = document.getElementById('idNum');
const btnRegister = document.getElementById('btnRegister');
var isPhoneCorrect = false;
var isEmailCorrect = false;
var isPasswordCorrect = false;
var isFirstNameCorrect = false;
var isLastNameCorrect = false;
var isIDCorrect = false;
const hideTimeout=null;


const Toast={
  init(){
    
    this.el=document.createElement('div');
    this.el.className='toast';
    document.body.appendChild(this.el);
  },
  show(message,state){
    this.hideTimeout=null;

    //clearTimeout(this,hideTimeout);

    this.el.textContent=message;
    this.el.className='toast toast--visible';
    if(state){
      this.el.classList.add(`toast--${state}`);
    }
    this.hideTimeout=setTimeout(()=>{
      this.el.classList.remove('toast--visible');

    },4000);

  }
};
document.addEventListener('DOMContentLoaded',()=>Toast.init());

const usersCollection = database.collection('Patient');

btnRegister.addEventListener('click', e => {
  // e.preventDefault();
  validateAllEditText();

if (isPhoneCorrect==true && isEmailCorrect==true &&isPasswordCorrect==true &&isFirstNameCorrect==true&&isLastNameCorrect==true&&isIDCorrect==true){
  const ID = usersCollection.doc(phone.value).set({
    Email: email.value,
    "FirstName": first_name.value,
    ID: idNum.value,
    "LastName":last_name.value,
    Password: password.value


  })
  .then(()=>{
    Toast.show('User Created!! ','success');
  var delayInMilliseconds = 4500; //1 second

  setTimeout(function() {
    window.location.replace("../html/patLogin.html");
  }, delayInMilliseconds);
      
        console.log('Data has been saved successfully !')})
      .catch(error => {
        console.error(error)
      });
  }else{
    Toast.show('One or more fields might are empty or invalid ','error')
    //alert('One or more fields might are empty or invalid ')
  }
 
  
});





// Validate Functions
function validateAllEditText(){
  validatePhone(phone);  
  validateEmail(email);
  validatePassword(password);
  validateFirstName(first_name);
  validateLastName(last_name);
  validateID(idNum);
 
}
function validatePhone(phone){
  isPhoneCorrect = true;
  if (phone.value==""){
    isPhoneCorrect = false;
      document.getElementById('phone').placeholder="Please do not leave phone number field empty";   
   }
}

function validateEmail(email) {
  isEmailCorrect=true;
  if (email.value=='') {
    // Email is good
    document.getElementById('email').placeholder="Please do not leave email field empty";
    isEmailCorrect=false;
    
  } 
   
}
function validatePassword(password) {
  isPasswordCorrect=true;
  if (password.value=='') {
    document.getElementById('password').placeholder="Please do not leave password field empty ";
    isPasswordCorrect=false;
  }
}
  

function validateFirstName(first_name) {
  isFirstNameCorrect=true;
  if (first_name.value=='') {
    document.getElementById('first_name').placeholder="Fill first name";
    isFirstNameCorrect=false;
  }
 
}

function validateLastName(last_name) {
  isLastNameCorrect=true;
  if (last_name.value=='') {
    document.getElementById('last_name').placeholder="Fill last name";
    isLastNameCorrect=false;  
  }
}

function validateID(idNum) {
  isIDCorrect=true;
  if (idNum.value=='') {
    document.getElementById('idNum').placeholder="Fill ID number ";
    isIDCorrect=false;
  }
}

