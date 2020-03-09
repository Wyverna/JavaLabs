/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBElement;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;

// import java content classes generated by binding compiler
import primer.po.*;

/*
 * $Id: Main.java,v 1.2 2009-11-11 14:17:31 pavel_bucek Exp $
 */
 
public class Main {
    
    // This sample application demonstrates how to construct value classes
    // and create a java content tree from scratch and marshal it
    // to XML data
    
    public static void main( String[] args ) {
        // create an empty PurchaseOrder
        PurchaseOrderType po = new PurchaseOrderType();
        
        // set the required orderDate attribute
        po.setOrderDate( getDate() );
        
        // create shipTo USAddress object
        USAddress shipTo = createUSAddress( "Alice Smith",
                                            "123 Maple Street",
                                            "Cambridge",
                                            "MA",
                                            "12345" );
                                            
        // set the required shipTo address 
        po.setShipTo( shipTo );
        
        // create billTo USAddress object
        USAddress billTo = createUSAddress( "Robert Smith",
                                            "8 Oak Avenue",
                                            "Cambridge",
                                            "MA",
                                            "12345" );
        
        // set the requred billTo address
        po.setBillTo( billTo );
                                            
        // create an empty Items object
        Items items = new Items();
        
        // get a reference to the ItemType list
        List<Items.Item> itemList = items.getItem();
        
        // start adding ItemType objects into it
        itemList.add( createItem( "Nosferatu - Special Edition (1929)", 
                                  5, 
                                  new BigDecimal( "19.99" ), 
                                  null,
                                  null,
                                  "242-NO" ) );
        itemList.add( createItem( "The Mummy (1959)", 
                                  3, 
                                  new BigDecimal( "19.98" ), 
                                  null,
                                  null,
                                  "242-MU" ) );
        itemList.add( createItem( "Godzilla and Mothra: Battle for Earth/Godzilla vs. King Ghidora", 
                                  3, 
                                  new BigDecimal( "27.95" ), 
                                  null,
                                  null,
                                  "242-GZ" ) );
        
        // set the required Items list
        po.setItems( items );
       
        // create an element for marshalling
        JAXBElement<PurchaseOrderType> poElement = (new ObjectFactory()).createPurchaseOrder(po);

        // create a Marshaller and marshal to System.out
        JAXB.marshal( poElement, System.out );
    }
    
    public static USAddress createUSAddress(  String name, String street,
                                              String city, String state,
                                              String zip ) {
    
        // create an empty USAddress objects                                             
        USAddress address = new USAddress();
       
        // set properties on it
        address.setName( name );
        address.setStreet( street );
        address.setCity( city );
        address.setState( state );
        address.setZip( new BigDecimal( zip ) );
        
        // return it
        return address;
    }
    
    public static Items.Item createItem( String productName,
                                         int quantity,
                                         BigDecimal price,
                                         String comment,
                                         XMLGregorianCalendar shipDate,
                                         String partNum ) {
   
        // create an empty ItemType object
        Items.Item item = new Items.Item();
        
        // set properties on it
        item.setProductName( productName );
        item.setQuantity( quantity );
        item.setUSPrice( price );
        item.setComment( comment );
        item.setShipDate( shipDate );
        item.setPartNum( partNum );
        
        // return it
        return item;
    }
                                           
                                    
    private static XMLGregorianCalendar getDate() {
	try {
	    return DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
	} catch (DatatypeConfigurationException e) {
	    throw new Error(e);
	}
    }
}
