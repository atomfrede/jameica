/**********************************************************************
 * $Source: /cvsroot/jameica/jameica/src/de/willuhn/jameica/gui/internal/views/Attic/ErrorView.java,v $
 * $Revision: 1.2 $
 * $Date: 2004/10/25 17:59:15 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn.webdesign
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.gui.internal.views;

import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.I18N;
import de.willuhn.util.Logger;

/**
 * Diese Fehlerseite wird angezeigt, wenn eine andere View in der
 * Methode bind() eine Exception nicht gefangen hat. Der Text dieser
 * Exception wird dann hier angezeigt. Zum Verstaendnis: Eine ErrorView
 * kann durchaus im regulaeren Betrieb verwendet werden, indem die
 * View ihre Exceptions nicht faengt. Allerdings sollte die View dann
 * aber darauf achten, dass Exceptions mit lokalisierten und fuer den
 * Benutzer verstaendlichen Texten geworfen werden. Im Gegensatz dazu wird
 * die View <code>FatalErrorView</code> wenn ein fataler Fehler aufgetreten
 * ist, der nicht zum geplanten Anwendungsablauf gehoert. Daher wird
 * dort dann auch der gesamte Stacktrace ausgegeben. Hier jedoch nicht.
 * Sprich: Dieser Dialog hier kann bei regulaeren und einkalkulierten
 * Anwendungsfehlern verwendet werden.
 */
public class ErrorView extends AbstractView
{

  /**
   * Die Exception steht zwar hier in der Methoden-Signatur
   * drin, wird jedoch nie geworfen, weil es sonst zu einem
   * Loop kommen wuerde.
   * @see de.willuhn.jameica.gui.views.AbstractView#bind()
   */
  public void bind() throws Exception
  {
  	I18N i18n = Application.getI18n();
		LabelGroup group = new LabelGroup(getParent(),i18n.tr("Fehler"));
		String unknownError = "Es ist ein unerwarteter Fehler aufgetreten.\n" +
			"Pr�fen Sie bitte das Systemprotokoll (durch Klick auf\n" +
			"den linken Teil der Status-Leiste im unteren Fensterrand).";

  	try {
  		Exception e = (Exception) getCurrentObject();
  		
  		if (e != null && e.getMessage() != null)
	  		group.addText(e.getMessage(),true);
	  	else
	  		group.addText(unknownError,false);
  	}
  	catch (Exception e)
  	{
			group.addText(unknownError,false);
  		Logger.error("exception while binding error page",e);
  	}
  }

  /**
   * @see de.willuhn.jameica.gui.views.AbstractView#unbind()
   */
  public void unbind() throws ApplicationException
  {
  }

}


/**********************************************************************
 * $Log: ErrorView.java,v $
 * Revision 1.2  2004/10/25 17:59:15  willuhn
 * @N aenderbare Tabellen
 *
 * Revision 1.1  2004/10/08 13:38:20  willuhn
 * *** empty log message ***
 *
 * Revision 1.15  2004/07/21 23:54:54  willuhn
 * @C massive Refactoring ;)
 *
 * Revision 1.14  2004/07/09 00:12:47  willuhn
 * @C Redesign
 *
 * Revision 1.13  2004/06/30 20:58:39  willuhn
 * *** empty log message ***
 *
 * Revision 1.12  2004/04/12 19:15:58  willuhn
 * @C refactoring
 * @N forms
 *
 * Revision 1.11  2004/03/30 22:08:26  willuhn
 * *** empty log message ***
 *
 * Revision 1.10  2004/03/03 22:27:10  willuhn
 * @N help texts
 * @C refactoring
 *
 * Revision 1.9  2004/02/20 20:45:24  willuhn
 * *** empty log message ***
 *
 * Revision 1.8  2004/01/28 20:51:25  willuhn
 * @C gui.views.parts moved to gui.parts
 * @C gui.views.util moved to gui.util
 *
 * Revision 1.7  2004/01/25 18:39:56  willuhn
 * *** empty log message ***
 *
 * Revision 1.6  2004/01/23 00:29:03  willuhn
 * *** empty log message ***
 *
 **********************************************************************/