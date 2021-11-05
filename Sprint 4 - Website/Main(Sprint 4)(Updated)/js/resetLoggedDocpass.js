var firebaseConfig = {
  apiKey: "AIzaSyABC45epEv116XDWIa-Oag1g9jzjyLDBrY",
  authDomain: "mpt-web-tester.firebaseapp.com",
  projectId: "mpt-web-tester",
  storageBucket: "mpt-web-tester.appspot.com",
  messagingSenderId: "818343226778",
  appId: "1:818343226778:web:67be5a6916ab3b02c71e23"  
};

firebase.initializeApp(firebaseConfig);
const auth = firebase.auth();
const database = firebase.firestore();
var Doctordata= {};
const oldPassword=document.getElementById('oldpassword');
const btnReset = document.getElementById('btnReset');
const password1=document.getElementById('password1');
const password2=document.getElementById('password2');
const usersCollection = database.collection('Doctor');
var oldPass;



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

window.addEventListener('load', () => {

  


});

btnReset.addEventListener('click', e => {
  // e.preventDefault();
  const phone = localStorage.getItem('Phone');
  console.log(phone);
const phoneCheck = database.collection('Doctor').doc(phone);
  phoneCheck.get()
  .then((docSnapshot) => {
    if (docSnapshot.exists) {
      phoneCheck.onSnapshot((doc) => {       
    Doctordata=docSnapshot.data();
        console.log(Doctordata['LastName']);
        console.log(Doctordata['Password']);
        oldPass=Doctordata['Password'];
        console.log(oldPassword)
  if(oldPassword.value !=Doctordata['Password']){
    Toast.show('Your old password does not match to whats on record !','error')

  }

  else if(password1.value!=password2.value){
      Toast.show('Your new passwords do not match !','error')

  }else{
     usersCollection.doc(phone).update({
      "Password": document.getElementById("password1").value 
    })
    .then(()=>{
      //alert('Info saved!!')
      Toast.show('New Password has been updated successfully !','success')
      var delayInMilliseconds = 4100; //1 second
      setTimeout(function() {
          window.location.replace("../HTML/updatedSettingsD.html");
        }, delayInMilliseconds);
      console.log('Data has been saved successfully !')})
    .catch(error => {
      
      console.error(error)
    }); 
  }
       });
    } else {
      Toast.show('Not found!! ','error')// create the document
    }
  });
  ;
  
  
 
  
});


