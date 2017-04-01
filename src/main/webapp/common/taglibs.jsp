<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- <%@ taglib uri="http://www.anrhdframework.org/app/tags" prefix="app" %>
<%@ taglib uri="http://www.anrhdframework.org/app/tags/validator" prefix="appv" %>
<%@ taglib uri="http://www.anrhdframework.org/app/tags/theme" prefix="appt" %>
<%@ taglib uri="/WEB-INF/mytaglib.tld" prefix="appcc" %> --%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sfix" value=".do"/>
<c:set var="app_title" value="${applicationScope.app_param.app_title}"/>
		
<c:set var="datePattern" value="yyyy-MM-dd"/>
<c:set var="timePattern" value="HH:mm:ss"/>
<c:set var="dateTimePattern" value="yyyy-MM-dd HH:mm:ss"/>

<c:set var="amountPattern" value="###0.00#"/>
