<div class="filedcontain">
	<label for="firstName">
		First Name
	</label>
	<g:textField name="firstName" value="${firstName}"/>	
</div>
<br/>
<div class="filedcontain">
	<label for="comments">
		Comments
	</label>
	<g:textArea name="comments" value="${comments}"/>	
</div>
<br/>
<div class="filedcontain">
	<label for="password">
		Password
	</label>
	<g:passwordField name="password" value="${password}"/>	
</div>
<br/>
<div class="filedcontain">
	<label for="notify">
		Do you want email notification?
	</label>
	<g:checkBox name="notify" value="${notify}"/>	
</div>
<br/>
<div class="filedcontain">
	<label for="gender">
		Gender: 
	</label>
	<g:radioGroup name="gender" labels="["Male","Female"]" values="["M","F"]" value="${gender}">
		${it.label} ${it.radio}
	</g:radioGroup>
</div>

<br/>
<div class="filedcontain">
	<label for="marketing">
		How did you hear about us? 
	</label>
	<g:select from="["Internet","Friend","LinkedIn","Other"]" name="marketing"  value="${marketing}"/>

</div>

