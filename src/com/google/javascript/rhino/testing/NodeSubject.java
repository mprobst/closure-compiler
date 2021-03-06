/*
 *
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1997-1999
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Bob Jervis
 *   Google Inc.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU General Public License Version 2 or later (the "GPL"), in which
 * case the provisions of the GPL are applicable instead of those above. If
 * you wish to allow use of your version of this file only under the terms of
 * the GPL and not to allow others to use your version of this file under the
 * MPL, indicate your decision by deleting the provisions above and replacing
 * them with the notice and other provisions required by the GPL. If you do
 * not delete the provisions above, a recipient may use your version of this
 * file under either the MPL or the GPL.
 *
 * ***** END LICENSE BLOCK ***** */

package com.google.javascript.rhino.testing;

import static com.google.common.truth.Truth.assertAbout;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import javax.annotation.CheckReturnValue;

/**
 * A Truth Subject for the Node class. Usage:
 *
 * <pre>
 *   import static com.google.javascript.rhino.testing.NodeSubject.assertNode;
 *   ...
 *   assertNode(node1).isEqualTo(node2);
 *   assertNode(node1).hasType(Token.FUNCTION);
 * </pre>
 */
public final class NodeSubject extends Subject<NodeSubject, Node> {
  @CheckReturnValue
  public static NodeSubject assertNode(Node node) {
    return assertAbout(nodes()).that(node);
  }

  public static Subject.Factory<NodeSubject, Node> nodes() {
    return NodeSubject::new;
  }

  private NodeSubject(FailureMetadata failureMetadata, Node node) {
    super(failureMetadata, node);
  }

  @Override
  public void isEqualTo(Object o) {
    check().that(actual()).isInstanceOf(Node.class);
    Node node = (Node) o;

    check("checkTreeEquals(%s)", node).that(actual().checkTreeEquals(node)).isNull();
  }

  public void hasType(Token type) {
    hasToken(type);
  }

  public void hasToken(Token token) {
    check("getToken()").that(actual().getToken()).isEqualTo(token);
  }

  public void isName(String name) {
    check("isName()").that(actual().isName()).isTrue();
    check("getString()").that(actual().getString()).isEqualTo(name);
  }

  public void matchesQualifiedName(String qname) {
    check("matchesQualifiedName(%s)", qname).that(actual().matchesQualifiedName(qname)).isTrue();
  }

  public void hasCharno(int charno) {
    check("getCharno()").that(actual().getCharno()).isEqualTo(charno);
  }

  public void hasLineno(int lineno) {
    check("getLineno()").that(actual().getLineno()).isEqualTo(lineno);
  }

  public void hasLength(int length) {
    check("getLength()").that(actual().getLength()).isEqualTo(length);
  }
}
