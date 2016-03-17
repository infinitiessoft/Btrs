package serviceTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import dao.PhotoDao;
import dao.ReportDao;
import entity.Photo;
import entity.Report;
import resources.specification.PhotoSpecification;
import resources.specification.SimplePageRequest;
import sendto.PhotoSendto;
import serviceImpl.PhotoServiceImpl;

public class PhotoServiceImplTest extends ServiceTest {

	private PhotoDao photoDao;
	private PhotoServiceImpl photoService;
	private ReportDao reportDao;
	private Photo photo;
	private Report report;

	@Before
	public void setUp() throws Exception {
		photoDao = context.mock(PhotoDao.class);
		reportDao = context.mock(ReportDao.class);
		photoService = new PhotoServiceImpl(photoDao, reportDao);
		photo = new Photo();
		photo.setId(1L);
		photo.setFileName("demo");
		photo.setTitle("Trip");
		report = new Report();
		report.setId(1L);
		photo.setReport(report);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(photoDao).findOne(1L);
				will(returnValue(photo));
			}
		});
		PhotoSendto ret = photoService.retrieve(1);
		assertEquals(1l, ret.getId().longValue());
		assertEquals("demo", ret.getFileName());
		assertEquals("Trip", ret.getTitle());
		assertEquals(report.getId(), ret.getReport().getId());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(photoDao).delete(photo);

				exactly(1).of(photoDao).findOne(1L);
				will(returnValue(photo));
			}
		});
		photoService.delete(1l);
	}

	@Test
	public void testSave() {
		final PhotoSendto newEntry = new PhotoSendto();
		newEntry.setFileName("F_name");
		PhotoSendto.Report rpt = new PhotoSendto.Report();
		rpt.setId(1L);
		newEntry.setReport(rpt);
		context.checking(new Expectations() {

			{
				exactly(1).of(reportDao).findOne(1L);
				will(returnValue(report));

				exactly(1).of(photoDao).save(with(any(Photo.class)));
				will(new CustomAction("save Photo") {

					@Override
					public Object invoke(Invocation invocation) throws Throwable {
						Photo e = (Photo) invocation.getParameter(0);
						e.setId(2L);
						return e;
					}
				});
			}
		});
		PhotoSendto ret = photoService.save(newEntry);
		assertEquals(2l, ret.getId().longValue());
		assertEquals(newEntry.getFileName(), ret.getFileName());
		assertEquals(newEntry.getTitle(), ret.getTitle());
	}

	@Test
	public void testUpdate() {
		final PhotoSendto newEntry = new PhotoSendto();
		newEntry.setFileName("F_name");
		newEntry.setTitle("Trip");
		context.checking(new Expectations() {

			{
				exactly(1).of(photoDao).save(photo);
				will(returnValue(photo));

				exactly(1).of(photoDao).findOne(photo.getId());
				will(returnValue(photo));
			}
		});
		PhotoSendto ret = photoService.update(1l, newEntry);
		assertEquals(1l, ret.getId().longValue());
		assertEquals(newEntry.getFileName(), ret.getFileName());
		assertEquals(newEntry.getTitle(), ret.getTitle());
	}

	@Test
	public void testFindAll() {
		final PhotoSpecification spec = new PhotoSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id", "ASC");
		final List<Photo> photos = new ArrayList<Photo>();
		photos.add(photo);
		final Page<Photo> page = new PageImpl<Photo>(photos);
		context.checking(new Expectations() {

			{
				exactly(1).of(photoDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<PhotoSendto> rets = photoService.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		PhotoSendto ret = rets.iterator().next();
		assertEquals(1l, ret.getId().longValue());
		assertEquals(photo.getFileName(), ret.getFileName());
		assertEquals(photo.getTitle(), ret.getTitle());
	}
}
