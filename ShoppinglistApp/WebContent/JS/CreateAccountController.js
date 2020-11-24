"use strict";

let userRoot;
let passRoot;
let repeatPassRoot;
let userEl;
let passEl;
let repeatPassEl;
document.addEventListener("DOMContentLoaded",init);

function init() {
	userRoot = document.getElementById("userroot");
	passRoot = document.getElementById("passroot");
	repeatPassRoot = document.getElementById("repeatpassroot");
	userEl = userRoot.querySelector("input[id='username']");
	passEl = passRoot.querySelector("input[id='password']");
	repeatPassEl = repeatPassRoot.querySelector("input[id='repeatpassword']");
	userEl.style.border = "solid red";
	userEl.addEventListener("input",validateUsername);
	passEl.addEventListener("input",validatePassword);
	repeatPassEl.addEventListener("input",validateRepeatPassword);
}

function validateUsername() {
	const username = userEl.value;
	if(username.match(/^[0-9a-zA-ZæøåÆØÅ |_-]{3,20}$/)) {
		userEl.style.border = "solid green";
	}
	else {
		userEl.style.border = "solid red";
	}
}

function validatePassword() {
	
}

function validateRepeatPassword() {
	
}