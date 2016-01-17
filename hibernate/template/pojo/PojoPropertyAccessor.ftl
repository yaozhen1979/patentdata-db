<#foreach property in pojo.getAllPropertiesIterator()>
    ${pojo.getPropertyGetModifiers(property)} 
    ${pojo.getJavaTypeName(property, jdk5)} 
    ${pojo.getGetterSignature(property)}() {
        return this.${property.name};
    }
    
    ${pojo.getPropertySetModifiers(property)} void set${pojo.getPropertyName(property)}
        (${pojo.getJavaTypeName(property, jdk5)} ${property.name}) 
    {
        this.${property.name} = ${property.name};
    }
</#foreach>
