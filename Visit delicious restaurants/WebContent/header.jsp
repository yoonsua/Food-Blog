<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <link rel="stylesheet" href="css/header.css">
<nav id="navbar">
    <ul>
        <li><p id="navbar_brand">J S P �Խ��� �� ����Ʈ</p></li>
        <li><p><a href="main.jsp">����</a></p></li>
        <li><p><a href="bbs.jsp">�Խ���</a></p></li>
        <%
        //������ ����
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
			System.out.println(userID);
		} 
        	if(userID==null){
        %>
        <li id="dropdown">
            <p>�����ϱ�</p>
            <ul id="dropdown_mark">
                <li><p><a href="login.jsp">�α���</a></p></li>
                <li><p><a href="join.jsp">ȸ������</a></p></li>
            </ul>
        </li>
         <%
        }else{
        %>
         <li id="dropdown">
            <p>ȸ������</p>
            <ul id="dropdown_mark">
                <li><p><a href="logoutAction.jsp">�α׾ƿ�</a></p></li>
            </ul>
        </li>
         <%
        }
        %>
    </ul>
</nav>
