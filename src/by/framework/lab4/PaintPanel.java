package by.framework.lab4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel {

	private ArrayList<GraphicPoint> points;
	private double scaleX;
	private double scaleY;

	private boolean showAxis = true;
	private boolean showMarkers = true;
	private boolean rotate = false;
	private boolean findSq = false;
	private boolean showMarkedPoints = false;

	private BasicStroke graphicsStroke;
	private BasicStroke axisStroke;
	private BasicStroke markerStroke;
	private Font axisFont;
	private BasicStroke sqStroke;
	private Font sqFont;

	private AffineTransform transform;

	private double minX, maxX, minY, maxY;
	private double dx = 0, dy = 0;

	PaintPanel() {
		setBackground(Color.GRAY);

		float[] dash2 = {20, 5, 5, 5, 5, 5, 10, 5, 10, 5};
		graphicsStroke = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, 
				BasicStroke.JOIN_BEVEL, 10f, dash2, 0f);

		axisStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, null, 0.0f);
		markerStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, null, 0.0f);
		axisFont = new Font("Serif", Font.BOLD, 14);

		sqStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, 
				BasicStroke.JOIN_MITER, 10.0f, null, 0.0f);
		axisFont = new Font("Serif", Font.PLAIN, 12);
	}

	public void paintGraphic(ArrayList<GraphicPoint> points) {
		this.points = null;
		this.points = points;
		repaint();
	}

	public void setShowAxis(boolean show) {
		showAxis = show;
		repaint();
	}

	public void setShowMarkers(boolean show) {
		this.showMarkers = show;
		repaint();
	}

	public void setRotate(boolean rotate) {
		this.rotate = rotate;
		repaint();
	}

	public void setFindSq(boolean find) {
		this.findSq = find;
		repaint();
	}

	public void setShowMarkedPoints(boolean show) {
		this.showMarkedPoints = show;
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(points == null || points.isEmpty()) return;

		Graphics2D canvas = (Graphics2D) g;
		Stroke oldStroke = canvas.getStroke();
		Color oldColor = canvas.getColor();
		Paint oldPaint = canvas.getPaint();
		Font oldFont = canvas.getFont();

		maxX = points.get(points.size()-1).getX();
		minX = points.get(0).getX();

		maxY = points.get(0).getY();
		minY = points.get(0).getY();

		for(int i = 1; i < points.size(); i++) {
			if(minY > points.get(i).getY())
				minY = points.get(i).getY();
			if(maxY < points.get(i).getY())
				maxY = points.get(i).getY();
		}

		scaleX = getSize().getWidth()/(maxX - minX);
		scaleY = getSize().getHeight()/(maxY - minY);

		System.out.println(getSize().getWidth() + " " + getSize().getHeight());
		System.out.println(this.getSize().getWidth() + " " + this.getSize().getHeight());

		dx = 0; dy = 0;
		if(rotate) {

			double X = getSize().getWidth()/2.0;
			double Y = getSize().getHeight()/2.0;

			scaleX = getSize().getHeight()/(maxX - minX);
			scaleY = getSize().getWidth()/(maxY - minY);

			double angle = -Math.PI/2.;
			
			dx = (Y - X) / scaleX;
			dy = (X - Y) / scaleY;
			if(transform == null) {
				transform = canvas.getTransform();
			}
//			transform.translate(X, Y);
//			transform.rotate(angle);
//			transform.translate(-X, -Y);
//			canvas.setTransform(transform);
//			transform.translate(X, Y);
//			transform.rotate(3*angle);
//			transform.translate(-X, -Y);
			canvas.rotate(angle, X, Y);
		} else {
//			if(transform == null) transform = canvas.getTransform();
//			transform.rotate(0);
//			canvas.setTransform(transform);
		}
		System.out.println();

		if(showAxis) paintAxis(canvas);
		printGraphic(canvas);
		if(findSq) paintSq(canvas);
		if(showMarkers) paintMarkers(canvas);

		canvas.setFont(oldFont);
		canvas.setPaint(oldPaint);
		canvas.setColor(oldColor);
		canvas.setStroke(oldStroke);
	}

	private void printGraphic(Graphics2D canvas) {		
		canvas.setStroke(graphicsStroke);
		canvas.setColor(Color.RED);
		GeneralPath graphics = new GeneralPath();
		boolean not_first = true;
		for(GraphicPoint graphicPoint : points) {
			Point2D.Double point = xyToPoint(graphicPoint.getX(), graphicPoint.getY());
			if(not_first) {
				graphics.moveTo(point.getX(), point.getY());
				not_first = false;
			} else {
				graphics.lineTo(point.getX(), point.getY());
			}
		}
		canvas.draw(graphics);
	}

	private void paintMarkers(Graphics2D g2) {
		g2.setStroke(markerStroke);
		g2.setColor(Color.RED);
		g2.setPaint(Color.RED);

		for(GraphicPoint p : points) {
			if(showMarkedPoints) {
				Double fx = Math.abs(p.getY());
				StringBuffer buff = new StringBuffer(fx.toString());
				int dot = buff.lastIndexOf(".");
				if(dot > 0 )
					buff.replace(dot, dot + 1, "");
				boolean flag = true;
				for(int i = 1; i < buff.length(); i++) {
					char c1 = buff.charAt(i - 1);
					char c2 = buff.charAt(i);
					//					int c3 = c2 - c1;
					if(c1 > c2) { // || c3 != 1
						flag = false;
						break;
					}
				}
				if(flag) {
					g2.setColor(Color.BLUE);
					g2.setPaint(Color.BLUE);
				} else {
					g2.setColor(Color.RED);
					g2.setPaint(Color.RED);
				}
			}

			Point2D.Double point = xyToPoint(p.getX(), p.getY());
			Point2D.Double corner = shiftPoint(point, 2, 2);
			Ellipse2D.Double ellipse = new Ellipse2D.Double();
			ellipse.setFrameFromCenter(point, corner);
			g2.draw(ellipse);
			g2.fill(ellipse);

			double W = 5;
			double W2 = W*2;
			Point2D.Double pointLine = shiftPoint(xyToPoint(p.getX(), p.getY()), -W, W);
			GeneralPath line = new GeneralPath();
			line.moveTo(pointLine.getX(), pointLine.getY());
			line.lineTo(line.getCurrentPoint().getX() + W2, line.getCurrentPoint().getY() - W2);
			g2.draw(line);

			pointLine = shiftPoint(xyToPoint(p.getX(), p.getY()), 0, W);
			line = new GeneralPath();
			line.moveTo(pointLine.getX(), pointLine.getY());
			line.lineTo(line.getCurrentPoint().getX(), line.getCurrentPoint().getY() - W2);
			g2.draw(line);

			pointLine = shiftPoint(xyToPoint(p.getX(), p.getY()), W, W);
			line = new GeneralPath();
			line.moveTo(pointLine.getX(), pointLine.getY());
			line.lineTo(line.getCurrentPoint().getX() - W2, line.getCurrentPoint().getY() - W2);
			g2.draw(line);

			pointLine = shiftPoint(xyToPoint(p.getX(), p.getY()), W, 0);
			line = new GeneralPath();
			line.moveTo(pointLine.getX(), pointLine.getY());
			line.lineTo(line.getCurrentPoint().getX() - W2, line.getCurrentPoint().getY());
			g2.draw(line);
		}
	}

	private void paintAxis(Graphics2D g2) {
		g2.setStroke(axisStroke);
		g2.setColor(Color.BLACK);
		g2.setPaint(Color.BLACK);
		g2.setFont(axisFont);
		FontRenderContext context = g2.getFontRenderContext();

		if(minX <= 0.0 && maxX >= 0.0) {
			g2.draw(new Line2D.Double(xyToPoint(0, maxY), xyToPoint(0, minY)));
			GeneralPath arrow = new GeneralPath();
			Point2D.Double lineEnd = xyToPoint(0, maxY);
			arrow.moveTo(lineEnd.getX(), lineEnd.getY());
			arrow.lineTo(arrow.getCurrentPoint().getX()+5, arrow.getCurrentPoint().getY()+20);
			arrow.lineTo(arrow.getCurrentPoint().getX()-10, arrow.getCurrentPoint().getY());
			arrow.closePath();
			g2.draw(arrow);
			g2.fill(arrow);
			Rectangle2D bounds = axisFont.getStringBounds("y", context);
			Point2D.Double labelPos = xyToPoint(0, maxY);
			g2.drawString("y", 
					(float)labelPos.getX() + 10, 
					(float)(labelPos.getY() - bounds.getY()));
		}

		if(minY <= 0.0 && maxY >= 0.0) {
			Line2D.Double lineX = new Line2D.Double(xyToPoint(minX, 0), xyToPoint(maxX, 0));
			g2.draw(lineX);
			GeneralPath arrow = new GeneralPath();
			Point2D.Double lineEnd = xyToPoint(maxX, 0);
			arrow.moveTo(lineEnd.getX(), lineEnd.getY());
			arrow.lineTo(arrow.getCurrentPoint().getX()-20, arrow.getCurrentPoint().getY()-5);
			arrow.lineTo(arrow.getCurrentPoint().getX(), arrow.getCurrentPoint().getY()+10);
			arrow.closePath();
			g2.draw(arrow);
			g2.fill(arrow);
			Rectangle2D bounds = axisFont.getStringBounds("x", context);
			Point2D.Double labelPos = xyToPoint(maxX, 0);
			g2.drawString("x", 
					(float)(labelPos.getX() - bounds.getWidth() - 10), 
					(float)(labelPos.getY() + bounds.getY()));
		}
	}

	private void paintSq(Graphics2D g2) {
		ArrayList<Integer> nulls = new ArrayList<Integer>();
		for(int i = 1; i < points.size(); i++) {
			if(Math.abs(0-points.get(i).getY()) <= 1E-10) {
				nulls.add(i);
			}
		}

		if(nulls.size() < 2) return;

		FontRenderContext context = g2.getFontRenderContext();

		for(int i = 0; i < nulls.size() - 1; i++) {
			g2.setStroke(sqStroke);
			g2.setColor(Color.YELLOW);
			g2.setPaint(Color.YELLOW);
			g2.setFont(sqFont);

			Double S = (points.get(nulls.get(i)).getY()/2.) 
					* (points.get(nulls.get(i) + 1).getX() - points.get(nulls.get(i)).getX());
			for(int j = nulls.get(i) + 1; j < nulls.get(i+1); j++) {
				S += (points.get(j).getY()/2.) 
						* (points.get(j + 1).getX() - points.get(j - 1).getX());
			}

			S += (points.get(nulls.get(i+1)).getY()/2.)
					* (points.get(nulls.get(i+1)).getX() - points.get(nulls.get(i+1) - 1).getX());

			GeneralPath sq = new GeneralPath();
			Point2D.Double start = xyToPoint(points.get(nulls.get(i)).getX(),
					points.get(nulls.get(i)).getY());
			sq.moveTo(start.getX(), start.getY());
			for(int j = nulls.get(i) + 1; j <= nulls.get(i+1); j++) {
				Point2D.Double np = xyToPoint(points.get(j).getX(), points.get(j).getY());
				sq.lineTo(np.getX(), np.getY());
			}
			sq.closePath();
			g2.draw(sq);
			g2.fill(sq);

			g2.setColor(Color.BLUE);
			g2.setPaint(Color.BLUE);
			StringBuffer sS = new StringBuffer(((Double)Math.abs(S)).toString());
			Rectangle2D bounds = axisFont.getStringBounds(sS.substring(0, 4), context);
			Point2D.Double labelPos = xyToPoint(
					(points.get(nulls.get(i + 1)).getX() + points.get(nulls.get(i)).getX())/2.0, 0);
			double w = points.get(nulls.get(i + 1) - 1).getY()>0?-1:1;
			g2.drawString(sS.substring(0, 4), 
					(float)(labelPos.getX() - bounds.getWidth()/2.), 
					(float)(labelPos.getY() + w*bounds.getHeight()));
		}
	}

	protected Point2D.Double xyToPoint(double x, double y) {
		double deltaX = x - minX;
		double deltaY = maxY - y;
		return new Point2D.Double((-dx + deltaX)*scaleX, (-dy + deltaY)*scaleY);
	}

	protected Point2D.Double shiftPoint(Point2D.Double src, double deltaX, double deltaY) {
		Point2D.Double dest = new Point2D.Double();
		dest.setLocation(src.getX() + deltaX, src.getY() + deltaY);
		return dest;
	}

}
