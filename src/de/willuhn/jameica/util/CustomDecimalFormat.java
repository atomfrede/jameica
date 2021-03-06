/**********************************************************************
 * $Source: /cvsroot/jameica/jameica/src/de/willuhn/jameica/util/CustomDecimalFormat.java,v $
 * $Revision: 1.1 $
 * $Date: 2012/04/23 21:03:42 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn software & services
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;

import de.willuhn.jameica.system.Application;

/**
 * Custom-Decimalformat
 */
public class CustomDecimalFormat extends DecimalFormat
{
  /**
   * ct.
   */
  public CustomDecimalFormat()
  {
    super("###,###,##0.00",new DecimalFormatSymbols(Application.getConfig().getLocale()));
    setGroupingUsed(true);
  }

  /**
   * Nachformatieren fuer "-0,00".
   * @see java.text.DecimalFormat#format(double, java.lang.StringBuffer,
   *      java.text.FieldPosition)
   */
  public StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition)
  {
    StringBuffer sb = super.format(number, result, fieldPosition);
    if (sb == null || sb.length() == 0)
      return sb;
    String s = sb.toString();
    if ("-0,00".equals(s))
    {
      sb.delete(0, sb.length());
      sb.append("0,00");
    }
    return sb;
  }
}

/*******************************************************************************
 * $Log: CustomDecimalFormat.java,v $
 * Revision 1.1  2012/04/23 21:03:42  willuhn
 * @N BUGZILLA 1227
 *
 ******************************************************************************/